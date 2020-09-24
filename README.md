# wob.city
Trainee Java Task "City"

[![CodeFactor](https://www.codefactor.io/repository/github/romeomihalovics/wob.city/badge)](https://www.codefactor.io/repository/github/romeomihalovics/wob.city)


The application represents cities, with name and population attributes. People can be differentiated by gender [male or female], and also, if a person is under age, he/she will be considered as a kid. Every people has a name, height, weight, age attribute. Each city has (/ could have, if there were more cities) a control center to verify it's people's informations. 

People have families, which have buildings, like apartment houses or family houses. In an apartment house multiple family is able to live. A family contains max two parents with opposing genders and max 3 children. 

People burn 350 kcal per minute and eat a random amount of a random food every 3 minutes that is available in the city to refill his/her energy levels. If he/she does not eat enough, and his/her energy levels reaches 0, he/she will die.

People age every 5 minutes, kids become adults if they're over or 18 yo. 18 years old or older people have a 10% chance of dying spontaneously every year. 60 years old people or older have a 25% chance of dying spontaneously every year. A person who reaches the 122 year age limit, will automatically die.

Some adults have professions, and some are criminals. Aprox. 10% of the population are police officers, 5% are paramedics, 10% are fire fighters and 8% are criminals. 

When someone dies, an available paramedic can revive him/her with CPR, with a 20% chance of success, within 2 minutes. If the person who died an available police officer will be also called, who will try to catch the criminal, and then kill him/her. The chance of the criminal getting caught increases 5% every time when he/she commits a crime.

When natural disasters destroy buildings, available fire fighters can save apartments, thus a family in it with a 15% chance of success. If the disaster destroys an apartment house, then after every 3rd flat, the chance of being saved by a fire fighter gets less with 10%.

A criminal will commit a crime every 5 minutes, where he/she will kill someone randomly.

Every 2 minutes, in a fertile family [which means there is a woman and a man "parent" roled person, both between the age of 20 and 40 and there is less then 3 kids in the family] a baby will born.

Each city has 3 news papers, Death News, New Born News, Consumption News. Every 10 minutes the news are published to an ftp server with the latest data fetched from the database, where every event is uploaded.

In the city, you will find foods. Foods are segregated to 4 categories, meat, dairy, grain, vegetables. Every food has details about itself, which are [by default smth/100g], protein, carbohydrate, fat. You can calculate the calories in a specific food with these details [by default /100g, but you could set it to what ever amount you like], or list all of them.

You can start natural disasters like volcanic eruptions, tornado, flooding in the cities, and some of them will kill more people, some of them will even have a continuation, like an aftershock for an earthquake that will kill even more people [the disaster destroys buildings, while killing the family(ies) in it].

... more stuff to come soon
