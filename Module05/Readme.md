Создать классы units.Car, ru.units.Truck, units.Bus. Каждый обладает объемом топлива и расходом (units.Car - 20\2.5, ru.units.Truck - 60\15, units.Bus - 40\7.5) и уникальных значением для определения среди разных транспортных средств.
Для каждого транспортного средства оставшиеся в баке количество топлива вычисляется раз в 3 сек.
Создать до 10 экземпляров разных транспортных средств и запустить их в работу
Создать класс FuelStaion. Одновременно может заправлять 3 автомобиля, остальные должны пока не освободится место. Заправка занимает 5 сек, зачем транспортное средство освобождает место
* Транспортные средства должны выстраиваться в очередь, если нет свобожных мест для заправки и начинать заправку в строгом порядке своей очередь
* Транспортные средства после заправки возвращаются на дорогу и продолжают свое движение