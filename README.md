# Java OOP Design
Academic Project of Java OOP Design (Practice).

## Design

### EnemyAircraft
During its creating time, the only class that can set the properties of the enemy should be the factory.
During its exiting time, only bullets and bombItem can affect its HP.
During its Elapsing time, only method vanish() can make it invalid.
### Bullet
Created by Factory,but set properties in Strategy.
### Item
Created by Factory,called by enemy.
