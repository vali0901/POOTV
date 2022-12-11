# POO TV
Pentru realizarea acestei etape, au fost create urmatoarele clase:
* clasele pentru parsarea inputului si outpututlui in format
  json (din pachetele input si output)
* clasa Database (din database), ce retine toate filmele din
  platforma si toi userii ce se pot loga (aici se vor retine
  informatiile despre userii noi in urma actiunii register)
* clasa App (din app) reprezinta instanta aplicatiei, unde
  sunt retinute informatii precum userul curent, pagina curenta
  si lista de filme vizibile la un moment dat de userul curent
* clasa Movie (din app) descrie un film si contine informatiile
  relevante si necesare (nume, actori, rating etc)
* clasa User (din app) descrie un user, ce contine credentiale
  si informatii utile precum lista de filme urmarite, cumparate,
  apreciate si care au primit rating.
* clasa Page si clasele utile PageFactory si PageHierarchy (din
  pages) descriu "sistemul ierarhic" al paginilor; initial am
  vrut sa creez o clasa abstracta Page ce va fi extinsa de
  clase specifice fiecarui tip de pagina, insa am considerat
  ca nu este nevoie intrucat paginile nu au caracteristici
  foarte specifice (mai putin paginile catre care se pot duce
  si actiunile pe care le pot face); in acest sens am creat
  clasa PageFactory care creeaza un obiect de tip Page ce contine
  actiunile si paginile specifice numelui acelei pagini (care se
  da ca parametru); totodata, in clasa PageHerarchy sunt retinute
  instantele fiecarui tip de pagina, pentru a nu supraincarca
  memoria inutil (stim ca e nevoie doar de o pagina de fiecare tip
  in sistemul ierarhic)
* clasa utila ActionMaker (din actions) are o metoda ce se ocupa cu
  parcurgerea inputului pentru actiuni, apelarea metodelor 
  corespunzatoare si crearea outputului

### Interactiuni
  Clasa ActionMaker este responsabila de infaptuirea efectiva a actiunilor.
  Aici se vor apela metode fie din Database, App sau User astfel:
* pentru Database sunt apelate metodele loginUser sau registerUser
  la intalnirea unei actiuni onPage de register/login
* pentru App este apelata metoda supraincarcata updateAvailableMovies, 
  in cazul unei actiuni de tip filter, search sau cand se schimba pagina
  curenta catre pagina movies
* pentru User sunt apelate metodele de purchase/watch/like/rate corespunzatoare
  actiunilor cu acelasi nume; metodele de like si rate interactioneaza cu
  un obiect de tip Movie, acestuia fiindu-i atribuite ratingul/aprecierea

### Design Patterns
  Pentru clasele App si Database s-a folosit patternul singleton
  intrucat nu este nevoie de mai mult de o instanta din fiecare clasa
  pe parcursul  executiei programului. Totodata instantele fiecarei
  pagini functioneaza similar unui singleton (intrucat este instantiat
  doar un obiect de tip Page pentru fiecare tip de pagina, alte obiecte
  de tip Page putand fi instantiate exclusiv in pachetul pages).
  Clasa PageFactory este similara unui design pattern de tip Factory,
  intrucat returneaza un obiect cu caracteristici specifice in functie
  numele pe care il primeste ca parametru.
  