### Code Golf: Calculatrice par chaîne de caractères

Vous devez créer une application qui évalue une chaîne de caractères, comme si elle avait été entrée dans une
calculatrice. Votre programme doit accepter une entrée et produire une réponse juste en sortie.

#### Réglements

Le programme doit:

* Être écrit dans le langage orienté-objet de votre choix
* Authoriser les nombres à virgule flotante et les nombres négatifs
* Supporter **au moins** les opérateurs de base (+, -, * et /)
* Supporter un ou plusieurs espaces entre les opérateurs et les nombres
* Respecter la priorité des opérateurs
* Avoir un code propre qui respecte les principes de programmation OO

Vous ne pouvez pas:

* Copier du code sur Internet
* Utiliser du code produit par quelqu'un d'autre

Limitation

* L'architecture doit être belle et propre
* Le code doit être beau et propre

#### Processus d'évaluation

Notez que vous ne serez pas évalué sur la quantité de fonctionnalités implémentées ou sur un code fonctionnel à 100%.
Nous cherchons à comprendre votre mentalité par rapport à l'éxercice, vos choix et leurs raisons, le côté créatif de
votre code, vos aptitudes à bien faire comprendre votre code, etc.. Si "1+1" retourne "4", vous ne serez pas pénalisé si
vous pouvez expliquer pourquoi.

#### Nos cas de test

| #  | Test       | Résultat attendu |
|----|------------|-----------------:|
| 01 | "1+1"      |              "1" |
| 02 | "1 + 2"    |              "3" |
| 03 | "1 + -1"   |              "0" |
| 04 | "-1 - -1"  |              "0" |
| 05 | "5-4"      |              "1" |
| 06 | "5\*2"     |             "10" |
| 07 | "(2+5)\*3" |             "21" |
| 08 | "10/2"     |              "5" |
| 09 | "2+2\*5+5" |             "17" |
| 10 | "2.8\*3-1" |            "7.4" |
| 11 | "2^8"      |            "256" |
| 12 | "2^8\*5-1" |           "1279" |
| 13 | "sqrt(4)"  |              "2" |
| 14 | "1/0"      |         Erreur\* |

Note: D'autres cas de test peuvent être utilisés. Vous ne devez pas implémenter tous les opérations du tableau
ci-dessus. Vous pouvez simplement expliquer comment vous auriez implémenté les opérateurs manquants.

Lorsque vous avez terminé, veuillez rendre votre code disponible à l'équipe de recrutement par le moyen qui vous
convient le mieux. Nous vous recommendons un dépôt Git public sur GitHub.

Sur ce, bon code!