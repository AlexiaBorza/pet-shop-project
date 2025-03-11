[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/JLYnumnD)
# Pet Shop Application
###  Student(i): Borza Andreea

## Descriere
Acest proiect gestionează activitățile unui petshop, fiind destinat administratorilor. Scopul sau monitorizarea inventarului, gestionarea comenzilor, programărilor pentru grooming sau consultații pentru aanimalute, și păstrarea unei evidențe clare a clienților. Aplicația va rula pe platforma desktop, utilizând Java Standard Edition și va folosi o bază de date relațională pentru a stoca informațiile.

## Obiective
Proiectul urmărește să ofere un set de funcționalități, precum:

* Gestionarea programărilor pentru servicii (grooming, consultații)
* Vizualizarea si editarea comenzilor plasate de clienți
* Gestionarea inventarului de produse(hrană, accesorii, jucării):
    - Adăugare
    - Editare
    - Eliminare
* Gestionarea animalelor disponibile:
    - Adăugare
    - Editare informatii si status (hranit, schimbat apa, curatat mediu)
    - Eliminare
* Stocarea și gestionarea datelor despre clienți și comenzile acestora

## Arhitectura
Proiectul este structurat conform principiilor programării orientate pe obiect. Aplicația include următoarele clase principale, care comunică între ele prin relații de asociere și compoziție:

![PetShopClassRelations (1)](https://github.com/user-attachments/assets/2b3d9c69-5e00-4cd7-8ec6-21f29dddd8fd)

* **Produs**: Reprezintă un element din inventar (hrană, accesorii etc.).
    - Atribute: `id`, `nume`, `categorie`, `pret`, `cantitateStoc`, 'disponibil'
* **Animal**: Reprezintă un animal disponibil pentru vânzare.
    - Atribute: `id`, `tip`, `rasa`, 'nume', `varsta`, `pret`, 'hranit', 'vaccinat'
* **Client**: Stochează informațiile despre clienți.
    - Atribute: `id`, `nume`, `prenume`, `telefon`, `email`
* **Comanda**: Reprezintă comenzile plasate de clienți, de unul sau mai multe produse.
    - Atribute: `id`, `data`, `client`, `total`, 'nr. produse'
* **Programare**: Gestionează programările pentru servicii de grooming și consultații.
    - Atribute: `data`, `ora`, `tipServiciu`, `pacient`

## Functionalitati/Exemple utilizare

1. **Adăugare Produs**: Operatorul poate adăuga un produs nou în inventar, completând detalii precum nume, categorie, preț și cantitate.
   
2. **Vizualizare Comenzi**: Operatorul poate vizualiza comenzile existente, detaliate pe baza produselor incluse în fiecare comandă și a informațiilor despre client.

3. **Căutare Client**: Funcție de căutare a unui client în baza de date pentru a consulta istoricul comenzilor și informațiile de contact.

4. **Programare Grooming**: Permite înregistrarea unei programări pentru un animalut, specificând serviciul dorit și programarea acestuia la o dată și oră.

5. **Actualizare Stocuri**: În urma plasării sau primirii unei comenzi, stocul fiecărui produs din comanda respectivă este actualizat automat.

6. **Status ingrijire animalute** Se poate verifica daca un animalut a fost hranit sau nu intr-o anume zi, cat si daca cusca sau acvariul au fost curatate la termen.

  
### Resurse
Markdown Guide, [Online] Available: https://www.markdownguide.org/basic-syntax/ [accesed: Mar 14, 1706]
