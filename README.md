# LaptopsInfoParser
Java додаток для збору статистики параметрів ноутбуків з json файлів

## Table of Contents
- [Usage](#usage)
- [Input and output files](#input-and-output-files)
- [Entities](#entities)
- [Experiments](#experiments)

## Usage
Після виконання `maven package` в папці target з'явиться файл LaptopsInfoParser-1.0-SNAPSHOT-jar-with-dependencies.jar.  
Для запуску програми потрібно ввести команду на зразок:
> ```
> java.exe -jar LaptopsInfoParser-1.0-SNAPSHOT-jar-with-dependencies.jar dir attribute

Де:
- dir - директорія, де знаходяться json файли, з яких буде збиратися статистика
- attribute - атрибут, за яким буде формуватися статистика

У консолі з'явиться інформація про отриману статистику, згенерований xml файл, час виконання та помилки.  
Статистику також можна переглянути у файлі statistics_by_{attribute}.xml.

## Input and output files
Вхідні json файли містять інформацію про моделі ноутбуків у такому форматі:
```json
[
  {
    "title": "MacBook Pro",
    "producer": "Apple",
    "processor": "Apple M1 Pro",
    "memory": "16GB DDR4",
    "optional_ports": "Thunderbolt 4, USB 4, SDXC, card slot"
  },
  {
    "title": "ThinkPad X1 Carbon",
    "producer": "Lenovo",
    "processor": "Intel Core i7-1185G7",
    "memory": "16GB LPDDR4x",
    "optional_ports": "HDMI, Thunderbolt 4, eSIM slot"
  },
  {
    "title": "Surface Laptop Studio",
    "producer": "Microsoft",
    "processor": "Intel Core i7-11800H",
    "memory": "32GB LPDDR4x",
    "optional_ports": "USB, Type-C"
  }
]
```
Вихідний xml файл зі статистикою має наступний вигляд:
```xml
<statistics>
    <item>
        <value>USB</value>
        <count>9</count>
    </item>
    <item>
        <value>HDMI</value>
        <count>6</count>
    </item>
    <item>
        <value>audio jack</value>
        <count>6</count>
    </item>
    <item>
        <value>Thunderbolt 4</value>
        <count>6</count>
    </item>
</statistics>
```
Де:
- value - значення атрибуту
- count - кількість ноутбуків із зазначеним атрибутом

## Entities
За основу дизайну додатка були взяті принципи SOLID, що виражається в першу чергу у використанні інтерфейсів, які
реалізуються функціональними сервісами. Усі методи посилаются переважно на інтерфейси, а не на їх реалізацію, для
дотримання Dependency Inversion.  
Основні сутності:
- Parser - інтерфейс для парсингу файлів та формування статистики
- ParserImpl - реалізація для Parser
- ParsersPool - інтерфейс для формування пулу потоків для паралельного парсингу файлів
- ParsersPoolImpl - реалізація для ParsersPool
- XMLBuilder - інтерфейс для створення xml файлу із статистикою
- XMLBuilderImpl - реалізація для XMLBuilder
- Console - інтерфейс для форматування виводу тексту в консоль
- ConsoleImpl - реалізація для Console
- Item - сутність для зберігання інформації про кількість ноутбуків з атрибутом
- Statistic - сутність для збергання списку елементів типу Item

## Experiments
Після завершення розробки був виконаний замір швидкодії додатка для різної кількості одночасно оброблюваних
файлів із наступними результатами:
- Для одного потоку - 1 мілісекунда
- Для двох потоків - 1 мілісекунда
- Для чотирьох потоків - 1-2 мілісекунди
- Для восьми потоків - 2 мілісекунди

**Висновок:** при збільшенні кількості потоків обробки файлів спостерігається тенденція до уповільнення виконання (але 
не стрімкого) через особливості синхронізації доступу до спільних ресурсів.