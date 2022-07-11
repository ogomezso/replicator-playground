# Replicator Uses Cases Docker Playground

## Run Environment

From root path run:

~~~shell
docker-compose up --build
~~~
## Schema Translation with Fresh SR

1. Create a datagen source connector (This will create a new subject on src Schema Registry) running `connect/submit_users_datagen.sh`
2. Set Import mode on Destiny SR by running `set_sr_mode_pre_translation.sh`
3. Create `schema_translation_replicator`  by running `replicator/submit_schema_translation_replicator.sh`
4. Check the schema was created on DEST Schema Registry with the same id by running `curl http://localhost:8082/subjects/datagen_users-value/versions/1`
5. Restore READWRITE mode on DEST SR by running  `set_sr_mode_post_translation.sh`.

### Warning
> Once you restore this mode new schemas created on SRC Schema Registry will be NOT REPLICATED to the DEST SR.
> Since DEST SCHEMA Registry will be not empty anymore your won't be able to put in IMPORT mode. 


## Schema Translation creating new Subjects on SRC SR

1. Create a datagen source connector (This will create a new subject on src Schema Registry) running `connect/submit_users_datagen.sh`
2. Set Import mode on Destiny SR by running `set_sr_mode_pre_translation.sh`
3. Create `schema_translation_replicator`  by running `replicator/submit_schema_translation_replicator.sh`
4. Check the schema was created on DEST Schema Registry with the same id by running `curl http://localhost:8082/subjects/datagen_users-value/versions/1`
5. Create a datagen source connector (This will create a new subject on src Schema Registry) running `connect/submit_orders_datagen.sh`
6. Check that the new schema was created on DEST Schema Registry by running `curl http://localhost:8082/subjects/datagen_orders-value/versions/1`
7. Restore READWRITE mode on DEST SR by running  `set_sr_mode_post_translation.sh`.
8. Create a new schema on SRC SR `curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"schema": "{\"type\": \"string\"}"}' http://localhost:8081/subjects/testTopic-value/versions`
9. Check that new schema is not created on DEST Schema Registry because it isn't on IMPORT mode

## Schema Translation creating new Subject on DEST SR

### hint
> Since to create a new subject on DEST SR you will need to be on READWRITE mode there won't be chance to do the schema translation anymore


## Schema Translation Facts
> What you really need is having the destination on import mode and empty:
>  - Id will be preserved as is on the Source Topic
>  - If destSR is not on import mode Schema Translation doesn't work
>  - When destSR is not empty you can't put it on import mode so you will never be able to import new Schemas


> Schema Translation is a great tool for fresh Schema Registry, you can run it as a one off think, or for use cases where destination Schema Registry will be on READ mode only, like of active-passive MRC scenarios

> You can include more than one topic but I would say that is better to have separate connector one just for schemas that is a one off thing and another one for topics.

### Schema topic migration with fresh DEST SR

1. Create a datagen source connector (This will create a new subject on src Schema Registry) running `connect/submit_users_datagen.sh`
2. Create `schema_migration_replicator`  by running `replicator/submit_schema_migration_replicator.sh`
3. Check the schema was created on DEST Schema Registry with the same id by running `curl http://localhost:8082/subjects/datagen_users-value/versions/1`
4. Create a datagen source connector (This will create a new subject on src Schema Registry) running `connect/submit_orders_datagen.sh`
5. Check that the new schema was created on DEST Schema Registry by running `curl http://localhost:8082/subjects/datagen_orders-value/versions/1`
## Schema topic migration, DEST SR with previously created subjects

1. Create a datagen source connector (This will create a new subject on src Schema Registry) running `connect/submit_users_datagen.sh`
2. Create `schema_migration_replicator`  by running `replicator/submit_schema_migration_replicator.sh`
3. Create a new schema on DEST SR `curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"schema": "{\"type\": \"string\"}"}' http://localhost:8082/subjects/destTestTopic-value/versions`
4. Create a new schema on SRC SR `curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"schema": "{\"type\": \"string\"}"}' http://localhost:8082/subjects/srcTtestTopic-value/versions`

### warning

> Replicator instance will now be able to replicate anymore and will fail

## Consumer Offset Translation (Java Consumer Example)

1. Create a datagen source connector (This will create a new subject on src Schema Registry) running `connect/submit_users_datagen.sh`
2. Create `schema_migration_replicator`  by running `replicator/submit_schema_migration_replicator.sh`
3. Check the schema was created on DEST Schema Registry with the same id by running `curl http://localhost:8082/subjects/datagen_users-value/versions/1`
4. Create `users_consumer_offset_replicator`by running `submit_consumer_offset_translation_replicator.sh`
5. Run `java-consumer` app by running `org.github.ogomezso.java.consumer.App.class` as main pass `application-src.yaml` as argument.
6. Stop `java-consumer`taking care of saving the last offset consumed
7. Run `java-consumer` app by running `org.github.ogomezso.java.consumer.App.class` as main pass `application-dest.yaml` as argument.
8. Check the new consumer is starting to consume from the **last commited offset** on SRC cluster.