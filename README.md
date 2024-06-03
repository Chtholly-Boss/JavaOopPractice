# Java OOP Design
Academic Project of Java OOP Design (Practice).

## Character
### Hero

### Enemy
Parameters should be set in the Corresponding factory.
Parameters:
- Position and Speed
- Move Pattern
- Shoot Strategy
- Bullet Type: Set by BulletFactory
## Item

## Bullet
Parameters should be set in the Corresponding factory.
Parameters:
- Position and Speed
- Power
- Move Pattern

## Strategy
### Shoot Strategy
Responsible for specifying a shoot Pattern,including: 
- Bullet Parameters
- Bullet Distributions

### Move Strategy
Responsible for updating a flying object's coordinate.
Consist of the followings: 
- Updating Logic
- Bound Check

## Utils
