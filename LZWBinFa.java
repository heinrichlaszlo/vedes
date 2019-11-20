

// LZWBinFa.java
//
// z3a7.cpp to (Z3A7.java) LZWBinFa.java, a z3a7.cpp mechanikus ĂˇtĂ­rata Java-ba.
//
// EgyĂĽtt tĂˇmadjuk meg: http://progpater.blog.hu/2011/04/14/egyutt_tamadjuk_meg
// LZW fa Ă©pĂ­tĹ‘ 3. C++ Ăˇtirata a C valtozatbol (+mĂ©lysĂ©g, atlag Ă©s szĂłrĂˇs)
// ProgramozĂł PĂˇternoszter
//
// Copyright (C) 2011, 2012, BĂˇtfai Norbert, nbatfai@inf.unideb.hu, nbatfai@gmail.com
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// Ez a program szabad szoftver; terjeszthetĂµ illetve mĂłdosĂ­thatĂł a
// Free Software Foundation Ăˇltal kiadott GNU General Public License
// dokumentumĂˇban leĂ­rtak; akĂˇr a licenc 3-as, akĂˇr (tetszĂµleges) kĂ©sĂµbbi
// vĂˇltozata szerint.
//
// Ez a program abban a remĂ©nyben kerĂĽl kĂ¶zreadĂˇsra, hogy hasznos lesz,
// de minden egyĂ©b GARANCIA NĂ‰LKĂśL, az ELADHATĂ“SĂ�GRA vagy VALAMELY CĂ‰LRA
// VALĂ“ ALKALMAZHATĂ“SĂ�GRA valĂł szĂˇrmaztatott garanciĂˇt is beleĂ©rtve.
// TovĂˇbbi rĂ©szleteket a GNU General Public License tartalmaz.
//
// A felhasznĂˇlĂłnak a programmal egyĂĽtt meg kell kapnia a GNU General
// Public License egy pĂ©ldĂˇnyĂˇt; ha mĂ©gsem kapta meg, akkor
// tekintse meg a <http://www.gnu.org/licenses/> oldalon.
//
//
// Version history:
//
// 0.0.1,       http://progpater.blog.hu/2011/02/19/gyonyor_a_tomor
// 0.0.2,       csomĂłpontok mutatĂłinak NULLĂˇzĂˇsa (nem fejtette meg senki :)
// 0.0.3,       http://progpater.blog.hu/2011/03/05/labormeres_otthon_avagy_hogyan_dolgozok_fel_egy_pedat
// 0.0.4,       z.cpp: a C verziĂłbĂłl svn: bevezetes/C/ziv/z.c ĂˇtĂ­rjuk C++-ra
//              http://progpater.blog.hu/2011/03/31/imadni_fogjatok_a_c_t_egy_emberkent_tiszta_szivbol
// 0.0.5,       z2.cpp: az fgv(*mut)-ok helyett fgv(&ref)
// 0.0.6,       z3.cpp: Csomopont beĂˇgyazva
//              http://progpater.blog.hu/2011/04/01/imadni_fogjak_a_c_t_egy_emberkent_tiszta_szivbol_2
// 0.0.6.1      z3a2.c: LZWBinFa mĂˇr nem barĂˇtja a Csomopont-nak, mert annak tagjait nem hasznĂˇlja direktben
// 0.0.6.2      Kis kommentezĂ©st teszĂĽnk bele 1. lĂ©pĂ©skĂ©nt (hogy a kicsit lemaradt hallgatĂłknak is
//              kĂ¶nnyebb legyen, jĂłl megtĹ±zdeljĂĽk tovĂˇbbi olvasmĂˇnyokkal)
//              http://progpater.blog.hu/2011/04/14/egyutt_tamadjuk_meg
//              (majd a 2. lĂ©pĂ©sben "beletesszĂĽk a d.c-t", majd s 3. lĂ©pĂ©sben a parancssorsor argok feldolgozĂˇsĂˇt)
// 0.0.6.3      z3a2.c: FejlesztgetjĂĽk a forrĂˇst: http://progpater.blog.hu/2011/04/17/a_tizedik_tizenegyedik_labor
// 0.0.6.4      SVN-beli, http://www.inf.unideb.hu/~nbatfai/p1/forrasok-SVN/bevezetes/vedes/
// 0.0.6.5      2012.03.20, z3a4.cpp: N betĹ±k (hiĂˇnyok), sorvĂ©gek, vezetĹ‘ komment figyelmen kĂ­vĂĽl: http://progpater.blog.hu/2012/03/20/a_vedes_elokeszitese
// 0.0.6.6      z3a5.cpp: mamenyaka kollĂ©ga Ă©szrevĂ©telĂ©re a tĂ¶bb komment sor figyelmen kĂ­vĂĽl hagyĂˇsa
//		http://progpater.blog.hu/2012/03/20/a_vedes_elokeszitese/fullcommentlist/1#c16150365
// 0.0.6.7	Javaslom ezt a verziĂłt vĂˇlasztani vĂ©dendĹ‘ programnak
// 0.0.6.8	z3a7.cpp: pĂˇr kisebb javĂ­tĂˇs, illetve a vĂ©dĂ©sek tĂˇmogatĂˇsĂˇhoz tovĂˇbbi komment a <<
// 		eltolĂł operĂˇtort tagfĂĽggvĂ©nykĂ©nt, illetve globĂˇlis fĂĽggvĂ©nykĂ©nt tĂşlterhelĹ‘ rĂ©szekhez.
//		http://progpater.blog.hu/2012/04/10/imadni_fogjak_a_c_t_egy_emberkent_tiszta_szivbol_4/fullcommentlist/1#c16341099
// prog2-re,    z3a7.cpp to Z3A7.java, teljesen mechanikus ĂˇtĂ­rĂˇs, nĂ©hĂˇny 
//              soronkĂ©nt a C++ kĂłdot betettem kommentbe Ă©s utĂˇna leĂ­rtam 
//              ugyanazt a nĂ©hĂˇny sort JavĂˇban. Ez tehĂˇt nem egy tiszta Java 
//              implementĂˇciĂł, from scratch, hanem egy szinte soronkĂ©nti ĂˇtĂ­rat, 
//              de Ă©ppen ezĂ©rt Ă©rdekes Ă¶sszevetni a C++ Ă©s a Java leĂ­rĂˇst.
//              Ennek megfelelĹ‘en a Java progi OO szerkezete ua., mint a C++ 
//              vĂˇltozat volt, a lĂ©nyegi mĹ±kĂ¶dĂ©st tekintve is helyesek a 
//              kommentek (de a JavĂˇban kimaradt a << operĂˇtor tĂşlterhelĂ©se, a 
//              destruktorok stb. termĂ©szetesen). 
//

/*
 * #include <iostream>	// mert olvassuk a std::cin, Ă­rjuk a std::cout
 * csatornĂˇkat #include <cmath>	// mert vonunk gyĂ¶kĂ¶t a szĂłrĂˇshoz: std::sqrt
 * #include <fstream>	// fĂˇjlbĂłl olvasunk, Ă­runk majd
 */

/*
 * Az LZWBinFa osztĂˇlyban absztrahĂˇljuk az LZW algoritmus binĂˇris fa Ă©pĂ­tĂ©sĂ©t.
 * Az osztĂˇly definĂ­ciĂłjĂˇba beĂˇgyazzuk a fa egy csomĂłpontjĂˇnak az absztrakt
 * jellemzĂ©sĂ©t, ez lesz a beĂˇgyazott Csomopont osztĂˇly. MiĂ©rt Ăˇgyazzuk be? Mert
 * kĂĽlĂ¶n nem szĂˇnunk neki szerepet, ezzel is jelezzĂĽk, hogy csak a fa rĂ©szekĂ©nt
 * szĂˇmiolunk vele.
 */

/*
 * class LZWBinFa {
 */

public class LZWBinFa implements Comparable<LZWBinFa> 
{
	
	@Override
	public int compareTo(LZWBinFa masikfa) 
  {
		try 
    {
			if(this.getMelyseg() < masikfa.getMelyseg()) 
      {
				return -1;
			} else if (this.getMelyseg() > masikfa.getMelyseg()) 
      {
				return 1;
			} else if (this.getAtlag() < masikfa.getAtlag()) 
      {
				return -1;
			} else if (this.getAtlag() > masikfa.getAtlag()) 
      {
				return 1;
			} else 
      {
				throw new NotComparable("Not Comparable");
			}
		} catch (NotComparable e){
			e.printStackTrace();
			return 0;
		}
	};
  /*
   * public:
   *
   */
  /*
   * Szemben a binĂˇris keresĹ‘fĂˇnkkal (BinFa osztĂˇly)
   * http://progpater.blog.hu/2011/04/12/imadni_fogjak_a_c_t_egy_emberkent_tiszta_szivbol_3
   * itt (LZWBinFa osztĂˇly) a fa gyĂ¶kere nem pointer, hanem a '/' betĂĽt
   * tartalmazĂł objektum, lĂˇsd majd a vĂ©dett tagok kĂ¶zĂ¶tt lent: Csomopont gyoker;
   * A fa viszont mĂˇr pointer, mindig az Ă©pĂĽlĹ‘ LZW-fĂˇnk azon csomĂłpontjĂˇra mutat,
   * amit az input feldolgozĂˇsa sorĂˇn az LZW algoritmus logikĂˇja diktĂˇl:
   * http://progpater.blog.hu/2011/02/19/gyonyor_a_tomor Ez a konstruktor annyit
   * csinĂˇl, hogy a fa mutatĂłt rĂˇĂˇllĂ­tja a gyĂ¶kĂ©rre. (Mert ugye laboron, blogon,
   * elĹ‘adĂˇsban tisztĂˇztuk, hogy a tartalmazott tagok, most "Csomopont gyoker"
   * konstruktora elĹ‘bb lefut, mint a tagot tartalmazĂł LZWBinFa osztĂˇly
   * konstruktora, Ă©ppen a kĂ¶vetkezĹ‘, azaz a fa=&gyoker OK.)
   */
  /*
   * LZWBinFa ():fa (&gyoker) { } ~LZWBinFa () { szabadit (gyoker.egyesGyermek
   * ()); szabadit (gyoker.nullasGyermek ()); }
   */

  public LZWBinFa() {

    fa = gyoker;

  }
  /* TagfĂĽggvĂ©nykĂ©nt tĂşlterheljĂĽk a << operĂˇtort, ezzel a cĂ©lunk, hogy felkeltsĂĽk a
     hallgatĂł Ă©rdeklĹ‘dĂ©sĂ©t, mert ekkor Ă­gy nyomhatjuk a fĂˇba az inputot: binFa << b; ahol a b
     egy '0' vagy '1'-es betĹ±.
     Mivel tagfĂĽggvĂ©ny, Ă­gy van rĂˇ "Ă©rtelmezve" az aktuĂˇlis (this "rejtett paramĂ©terkĂ©nt"
     kapott ) pĂ©ldĂˇny, azaz annak a fĂˇnak amibe Ă©ppen be akarjuk nyomni a b betĹ±t a tagjai
     (pl.: "fa", "gyoker") hasznĂˇlhatĂłak a fĂĽggvĂ©nyben.

     A fĂĽggvĂ©nybe programoztuk az LZW fa Ă©pĂ­tĂ©sĂ©nek algoritmusĂˇt tk.:
     http://progpater.blog.hu/2011/02/19/gyonyor_a_tomor

     a b formĂˇlis param az a betĹ±, amit Ă©ppen be kell nyomni a fĂˇba.
     
     a binFa << b (ahol a b majd a vĂ©gĂ©n lĂˇtszik, hogy mĂˇr az '1' vagy a '0') azt jelenti
     tagfĂĽggvĂ©nykĂ©nt, hogy binFa.operator<<(b) (globĂˇliskĂ©nt Ă­gy festene: operator<<(binFa, b) )
          
     */
  /*
  void operator<< (char b)
  {
    // Mit kell betenni Ă©ppen, '0'-t?
    if (b == '0')
      {
      * 
      */

  public void egyBitFeldolg(char b) {
    // Mit kell betenni Ă©ppen, '0'-t?
    if (b == '0') {
      /*
       * Van '0'-s gyermeke az aktuĂˇlis csomĂłpontnak? megkĂ©rdezzĂĽk TĹ‘le, a "fa"
       * mutatĂł Ă©ppen reĂˇ mutat
       */
      /*
       * if (!fa->nullasGyermek ())	// ha nincs, hĂˇt akkor csinĂˇlunk { //
       * elkĂ©szĂ­tjĂĽk, azaz pĂˇldĂˇnyosĂ­tunk a '0' betĹ± akt. parammal Csomopont *uj
       * = new Csomopont ('0'); // az aktuĂˇlis csomĂłpontnak, ahol Ăˇllunk azt
       * ĂĽzenjĂĽk, hogy // jegyezze mĂˇr be magĂˇnak, hogy nullĂˇs gyereke mostantĂłl
       * van // kĂĽldjĂĽk is Neki a gyerek cĂ­mĂ©t: fa->ujNullasGyermek (uj); // Ă©s
       * visszaĂˇllunk a gyĂ¶kĂ©rre (mert ezt diktĂˇlja az alg.) fa = &gyoker; } else
       * // ha van, arra rĂˇlĂ©pĂĽnk { // azaz a "fa" pointer mĂˇr majd a szĂłban
       * forgĂł gyermekre mutat: fa = fa->nullasGyermek (); } }
       *
       */
      if (fa.nullasGyermek() == null) // ha nincs, hĂˇt akkor csinĂˇlunk
      {
        // elkĂ©szĂ­tjĂĽk, azaz pĂˇldĂˇnyosĂ­tunk a '0' betĹ± akt. parammal
        Csomopont uj = new Csomopont('0');
        // az aktuĂˇlis csomĂłpontnak, ahol Ăˇllunk azt ĂĽzenjĂĽk, hogy
        // jegyezze mĂˇr be magĂˇnak, hogy nullĂˇs gyereke mostantĂłl van
        // kĂĽldjĂĽk is Neki a gyerek cĂ­mĂ©t:
        fa.ujNullasGyermek(uj);
        // Ă©s visszaĂˇllunk a gyĂ¶kĂ©rre (mert ezt diktĂˇlja az alg.)
        fa = gyoker;
      } else // ha van, arra rĂˇlĂ©pĂĽnk
      {
        // azaz a "fa" pointer mĂˇr majd a szĂłban forgĂł gyermekre mutat:
        fa = fa.nullasGyermek();
      }
    } // Mit kell betenni Ă©ppen, vagy '1'-et?
    /*
     * else { if (!fa->egyesGyermek ()) { Csomopont *uj = new Csomopont ('1');
     * fa->ujEgyesGyermek (uj); fa = &gyoker; } else { fa = fa->egyesGyermek ();
     * } } }
     *
     */ else {
      if (fa.egyesGyermek() == null) {
        Csomopont uj = new Csomopont('1');
        fa.ujEgyesGyermek(uj);
        fa = gyoker;
      } else {
        fa = fa.egyesGyermek();
      }
    }
  }
  /* A bejĂˇrĂˇssal kapcsolatos fĂĽggvĂ©nyeink (tĂşlterhelt kiir-Ăłk, atlag, ratlag stb.) rekurzĂ­vak,
     tk. a rekurzĂ­v fabejĂˇrĂˇst valĂłsĂ­tjĂˇk meg (lĂˇsd a 3. elĹ‘adĂˇs "FabejĂˇrĂˇs" c. fĂłliĂˇjĂˇt Ă©s tĂˇrsait)

     (Ha a rekurzĂ­v fĂĽggvĂ©nnyel ĂˇltalĂˇban gondod van => K&R kĂ¶nyv megfelelĹ‘ rĂ©sze: a 3. ea. izometrikus
     rĂ©szĂ©ben ezt "letĂˇncoltuk" :) Ă©s kĂĽlĂ¶n idĂ©ztĂĽk a K&R ĂˇllĂˇspontjĂˇt :)
   */
  /*
  void kiir (void)
  {
  * 
  */

  public void kiir() {
    // Sokkal elegĂˇnsabb lenne (Ă©s mĂˇs, a bevezetĂ©sben nem kibontandĂł reentrĂˇns kĂ©rdĂ©sek miatt is, mert
    // ugye ha most kĂ©t helyrĹ‘l hĂ­vjĂˇk meg az objektum ilyen fĂĽggvĂ©nyeit, tahĂˇt ha kĂ©tszer kezd futni az
    // objektum kiir() fgv.-e pl., az komoly hiba, mert elromlana a mĂ©lysĂ©g... tehĂˇt a mostani megoldĂˇsunk
    // nem reentrĂˇns) ha nem hasznĂˇlnĂˇnk a C verziĂłban globĂˇlis vĂˇltozĂłkat, a C++ vĂˇltozatban pĂ©ldĂˇnytagot a
    // mĂ©lysĂ©g kezelĂ©sĂ©re: http://progpater.blog.hu/2011/03/05/there_is_no_spoon
    melyseg = 0;
    // ha nem mondta meg a hĂ­vĂł az ĂĽzenetben, hogy hova Ă­rjuk ki a fĂˇt, akkor a
    // sztenderd out-ra nyomjuk
    /*
     * kiir (&gyoker, std::cout);
     *
     */
    kiir(gyoker, new java.io.PrintWriter(System.out));

  }
  /* mĂˇr nem hasznĂˇljuk, tartalmĂˇt a dtor hĂ­vja
  void szabadit (void)
  {
    szabadit (gyoker.egyesGyermek ());
    szabadit (gyoker.nullasGyermek ());
    // magĂˇt a gyĂ¶keret nem szabadĂ­tjuk, hiszen azt nem mi foglaltuk a szabad tĂˇrban (halmon).
  }
  */

  /* A vĂˇltozatossĂˇg kedvĂ©Ă©rt ezeket az osztĂˇlydefinĂ­ciĂł (class LZWBinFa {...};) utĂˇn definiĂˇljuk,
     hogy kĂ©nytelen lĂ©gy az LZWBinFa Ă©s a :: hatĂłkĂ¶r operĂˇtorral minĹ‘sĂ­tve definiĂˇlni :) l. lentebb */
  /*
  int getMelyseg (void);
  double getAtlag (void);
  double getSzoras (void);
  */
  /* VĂˇgyunk, hogy a felĂ©pĂ­tett LZW fĂˇt ki tudjuk nyomni ilyenformĂˇn: std::cout << binFa;
     de mivel a << operĂˇtor is a sztenderd nĂ©vtĂ©rben van, de a using namespace std-t elvbĹ‘l
     nem hasznĂˇljuk bevezetĹ‘ kurzusban, Ă­gy ez a konstrukciĂł csak az argfĂĽggĹ‘ nĂ©vfeloldĂˇs miatt
     fordul le (B&L kĂ¶nyv 185. o. teteje) Ăˇm itt nem az a lĂ©nyeg, hanem, hogy a cout ostream
     osztĂˇlybeli, Ă­gy abban az osztĂˇlyban kĂ©ne mĂłdosĂ­tani, hogy tudjon kiĂ­rni LZWBinFa osztĂˇlybelieket...
     e helyett a globĂˇlis << operĂˇtort terheljĂĽk tĂşl, 
     
     a kiFile << binFa azt jelenti, hogy
     
      - tagfĂĽggvĂ©nykĂ©nt: kiFile.operator<<(binFa) de ehhez a kiFile valamilyen
      std::ostream stream osztĂˇly forrĂˇsĂˇba kellene beleĂ­rni ezt a tagfĂĽggvĂ©nyt,
      amely ismeri a mi LZW binfĂˇnkat...
      
      - globĂˇlis fĂĽggvĂ©nykĂ©nt: operator<<(kiFile, binFa) Ă©s pont ez lĂˇtszik a kĂ¶vetkezĹ‘ sorban:
     
     */
  /*
  friend std::ostream & operator<< (std::ostream & os, LZWBinFa & bf)
  {
    bf.kiir (os);
    return os;
  }
  void kiir (std::ostream & os)
  {
    melyseg = 0;
    kiir (&gyoker, os);
  }
  * 
  */
  public void kiir(java.io.PrintWriter os) {
    melyseg = 0;
    kiir(gyoker, os);
  }

  /*
private:
  class Csomopont
  {
  public:
  * 
  */
  class Csomopont {

    /*
     * A paramĂ©ter nĂ©lkĂĽli konstruktor az elepĂ©rtelmezett '/' "gyĂ¶kĂ©r-betĹ±vel"
     * hozza lĂ©tre a csomĂłpontot, ilyet hĂ­vunk a fĂˇbĂłl, aki tagkĂ©nt tartalmazza a
     * gyĂ¶keret. MĂˇskĂĽlĂ¶nben, ha valami betĹ±vel hĂ­vjuk, akkor azt teszi a "betu"
     * tagba, a kĂ©t gyermekre mutatĂł mutatĂłt pedig nullra ĂˇllĂ­tjuk, C++-ban a 0
     * is megteszi.
     */
    /*
     * Csomopont (char b = '/'):betu (b), balNulla (0), jobbEgy (0) { };
     * ~Csomopont () { };
     *
     */
    public Csomopont(char betu) {
      this.betu = betu;
      balNulla = null;
      jobbEgy = null;
    }

    ;
    
    
    // AktuĂˇlis csomĂłpont, mondd meg nĂ©kem, ki a bal oldali gyermeked
    // (a C verziĂł logikĂˇjĂˇval mĹ±xik ez is: ha nincs, akkor a null megy vissza)
  /*
    Csomopont *nullasGyermek () const
    {
      return balNulla;
    }*/
    public Csomopont nullasGyermek() {
      return balNulla;
    }
    // AktuĂˇlis csomĂłpon,t mondd meg nĂ©kem, ki a jobb oldali gyermeked?
    /*
    Csomopont *egyesGyermek () const
    {
      return jobbEgy;
    }*/

    public Csomopont egyesGyermek() {
      return jobbEgy;
    }
    // AktuĂˇlis csomĂłpont, Ă­mhol legyen a "gy" mutatta csomĂłpont a bal oldali gyereked!
    /*
    void ujNullasGyermek (Csomopont * gy)
    {
      balNulla = gy;
    }*/

    public void ujNullasGyermek(Csomopont gy) {
      balNulla = gy;
    }
    // AktuĂˇlis csomĂłpont, Ă­mhol legyen a "gy" mutatta csomĂłpont a jobb oldali gyereked!
    /*
    void ujEgyesGyermek (Csomopont * gy)
    {
      jobbEgy = gy;
    }*/

    public void ujEgyesGyermek(Csomopont gy) {
      jobbEgy = gy;
    }
    // AktuĂˇlis csomĂłpont: Te milyen betĹ±t hordozol?
    // (a const kulcsszĂłval jelezzĂĽk, hogy nem bĂˇntjuk a pĂ©ldĂˇnyt)
    /*
    char getBetu () const
    {
      return betu;
    }*/

    public char getBetu() {
      return betu;
    }
    /*
     * private:
     */
    // friend class LZWBinFa; /* mert ebben a valtozatban az LZWBinFa metĂłdusai nem kĂ¶zvetlenĂĽl
    // a Csomopont tagjaival dolgoznak, hanem beĂˇllĂ­tĂł/lekĂ©rdezĹ‘ ĂĽzenetekkel Ă©rik el azokat */
    // Milyen betĹ±t hordoz a csomĂłpont
    private char betu;
    // Melyik mĂˇsik csomĂłpont a bal oldali gyermeke? (a C vĂˇltozatbĂłl "Ă¶rĂ¶kĂ¶lt" logika:
    // ha hincs ilyen csermek, akkor balNulla == null) igaz
    /*
     * Csomopont *balNulla; Csomopont *jobbEgy;
     *
     */
    private Csomopont balNulla = null;
    private Csomopont jobbEgy = null;
    // nem mĂˇsolhatĂł a csomĂłpont (Ă¶kĂ¶rszabĂˇly: ha van valamilye a szabad tĂˇrban,
    // letiltjuk a mĂˇsolĂł konstruktort, meg a mĂˇsolĂł Ă©rtĂ©kadĂˇst)
    /*
     * Csomopont (const Csomopont &); Csomopont & operator= (const Csomopont &);
     *
     */
  };

  /*
   * Mindig a fa "LZW algoritmus logikĂˇja szerinti aktuĂˇlis" csomĂłpontjĂˇra mutat
   */
  /*
   * Csomopont *fa;
   *
   */
  private Csomopont fa = null;
  // technikai
  private int melyseg, atlagosszeg, atlagdb;
  private double szorasosszeg;
  // szokĂˇsosan: nocopyable
  /*
  LZWBinFa (const LZWBinFa &);
  LZWBinFa & operator= (const LZWBinFa &);
  * 
  */

  /* KiĂ­rja a csomĂłpontot az os csatornĂˇra. A rekurziĂł kapcsĂˇn lĂˇsd a korĂˇbbi K&R-es utalĂˇst... */
  /*
  void kiir (Csomopont * elem, std::ostream & os)
  {
  * 
  */
  public void kiir(Csomopont elem, java.io.PrintWriter os) {
    // Nem lĂ©tezĹ‘ csomĂłponttal nem foglalkozunk... azaz ez a rekurziĂł leĂˇllĂ­tĂˇsa
    /*
     * if (elem != NULL) { ++melyseg; kiir (elem->egyesGyermek (), os); // ez a
     * postorder bejĂˇrĂˇshoz kĂ©pest // 1-el nagyobb mĂ©lysĂ©g, ezĂ©rt -1 for (int i =
     * 0; i < melyseg; ++i) os << "---"; os << elem->getBetu () << "(" << melyseg
     * - 1 << ")" << std::endl; kiir (elem->nullasGyermek (), os); --melyseg; }
     *
     */
    if (elem != null) {
      ++melyseg;
      kiir(elem.egyesGyermek(), os);
      // ez a postorder bejĂˇrĂˇshoz kĂ©pest
      // 1-el nagyobb mĂ©lysĂ©g, ezĂ©rt -1
      for (int i = 0; i < melyseg; ++i) {
        os.print("---");
      }
      os.print(elem.getBetu());
      os.print("(");
      os.print(melyseg - 1);
      os.println(")");
      kiir(elem.nullasGyermek(), os);
      --melyseg;
    }
  }
  /*
   * void szabadit (Csomopont * elem) { // Nem lĂ©tezĹ‘ csomĂłponttal nem
   * foglalkozunk... azaz ez a rekurziĂł leĂˇllĂ­tĂˇsa if (elem != NULL) { szabadit
   * (elem->egyesGyermek ()); szabadit (elem->nullasGyermek ()); // ha a
   * csomĂłpont mindkĂ©t gyermekĂ©t felszabadĂ­tottuk // azutĂˇn szabadĂ­tjuk magĂˇt a
   * csomĂłpontot: delete elem; } }
   */
  /*
   * protected:	// ha esetleg egyszer majd kiterjesztjĂĽk az osztĂˇlyt, mert
   */
// akarunk benne valami ĂşjdonsĂˇgot csinĂˇlni, vagy meglĂ©vĹ‘ tevĂ©kenysĂ©get mĂˇshogy... stb.
// akkor ezek lĂˇtszanak majd a gyerek osztĂˇlyban is

  /*
   * A fĂˇban tagkĂ©nt benne van egy csomĂłpont, ez erĹ‘sen ki van tĂĽntetve, Ĺ� a
   * gyĂ¶kĂ©r:
   */
  /*
   * Csomopont gyoker;
   *
   */
  protected Csomopont gyoker = new Csomopont('/');
  int maxMelyseg;
  double atlag, szoras;

  /*
  void rmelyseg (Csomopont * elem);
  void ratlag (Csomopont * elem);
  void rszoras (Csomopont * elem);
*/
  /*
};
*/
// NĂ©hĂˇny fĂĽggvĂ©nyt az osztĂˇlydefinĂ­ciĂł utĂˇn definiĂˇlunk, hogy lĂˇssunk ilyet is ... :)
// Nem erĹ‘ltetjĂĽk viszont a kĂĽlĂ¶n fĂˇjlba szedĂ©st, mert a sablonosztĂˇlyosĂ­tott tovĂˇbb
// fejlesztĂ©sben az linkelĂ©si gondot okozna, de ez a tĂ©ma mĂˇr kivezet a laborteljesĂ­tĂ©s
// szĂĽksĂ©ges feladatĂˇbĂłl: http://progpater.blog.hu/2011/04/12/imadni_fogjak_a_c_t_egy_emberkent_tiszta_szivbol_3
// EgyĂ©bkĂ©nt a melyseg, atlag Ă©s szoras fgv.-ek a kiir fgv.-el teljesen egy kaptafa.
/*
int
LZWBinFa::getMelyseg (void)
{
  melyseg = maxMelyseg = 0;
  rmelyseg (&gyoker);
  return maxMelyseg - 1;
}

double
LZWBinFa::getAtlag (void)
{
  melyseg = atlagosszeg = atlagdb = 0;
  ratlag (&gyoker);
  atlag = ((double) atlagosszeg) / atlagdb;
  return atlag;
}

double
LZWBinFa::getSzoras (void)
{
  atlag = getAtlag ();
  szorasosszeg = 0.0;
  melyseg = atlagdb = 0;

  rszoras (&gyoker);

  if (atlagdb - 1 > 0)
    szoras = std::sqrt (szorasosszeg / (atlagdb - 1));
  else
    szoras = std::sqrt (szorasosszeg);

  return szoras;
}

void
LZWBinFa::rmelyseg (Csomopont * elem)
{
  if (elem != NULL)
    {
      ++melyseg;
      if (melyseg > maxMelyseg)
	maxMelyseg = melyseg;
      rmelyseg (elem->egyesGyermek ());
      // ez a postorder bejĂˇrĂˇshoz kĂ©pest
      // 1-el nagyobb mĂ©lysĂ©g, ezĂ©rt -1
      rmelyseg (elem->nullasGyermek ());
      --melyseg;
    }
}

void
LZWBinFa::ratlag (Csomopont * elem)
{
  if (elem != NULL)
    {
      ++melyseg;
      ratlag (elem->egyesGyermek ());
      ratlag (elem->nullasGyermek ());
      --melyseg;
      if (elem->egyesGyermek () == NULL && elem->nullasGyermek () == NULL)
	{
	  ++atlagdb;
	  atlagosszeg += melyseg;
	}
    }
}

void
LZWBinFa::rszoras (Csomopont * elem)
{
  if (elem != NULL)
    {
      ++melyseg;
      rszoras (elem->egyesGyermek ());
      rszoras (elem->nullasGyermek ());
      --melyseg;
      if (elem->egyesGyermek () == NULL && elem->nullasGyermek () == NULL)
	{
	  ++atlagdb;
	  szorasosszeg += ((melyseg - atlag) * (melyseg - atlag));
	}
    }
}
*/
  public int getMelyseg() {
    melyseg = maxMelyseg = 0;
    rmelyseg(gyoker);
    return maxMelyseg - 1;
  }

  public double getAtlag() {
    melyseg = atlagosszeg = atlagdb = 0;
    ratlag(gyoker);
    atlag = ((double) atlagosszeg) / atlagdb;
    return atlag;
  }

  public double getSzoras() {
    atlag = getAtlag();
    szorasosszeg = 0.0;
    melyseg = atlagdb = 0;

    rszoras(gyoker);

    if (atlagdb - 1 > 0) {
      szoras = Math.sqrt(szorasosszeg / (atlagdb - 1));
    } else {
      szoras = Math.sqrt(szorasosszeg);
    }

    return szoras;
  }

  public void rmelyseg(Csomopont elem) {
    if (elem != null) {
      ++melyseg;
      if (melyseg > maxMelyseg) {
        maxMelyseg = melyseg;
      }
      rmelyseg(elem.egyesGyermek());
      // ez a postorder bejĂˇrĂˇshoz kĂ©pest
      // 1-el nagyobb mĂ©lysĂ©g, ezĂ©rt -1
      rmelyseg(elem.nullasGyermek());
      --melyseg;
    }
  }

  public void ratlag(Csomopont elem) {
    if (elem != null) {
      ++melyseg;
      ratlag(elem.egyesGyermek());
      ratlag(elem.nullasGyermek());
      --melyseg;
      if (elem.egyesGyermek() == null && elem.nullasGyermek() == null) {
        ++atlagdb;
        atlagosszeg += melyseg;
      }
    }
  }

  public void rszoras(Csomopont elem) {
    if (elem != null) {
      ++melyseg;
      rszoras(elem.egyesGyermek());
      rszoras(elem.nullasGyermek());
      --melyseg;
      if (elem.egyesGyermek() == null && elem.nullasGyermek() == null) {
        ++atlagdb;
        szorasosszeg += ((melyseg - atlag) * (melyseg - atlag));
      }
    }
  }

// teszt pl.: http://progpater.blog.hu/2011/03/05/labormeres_otthon_avagy_hogyan_dolgozok_fel_egy_pedat
// [norbi@sgu ~]$ echo "01111001001001000111"|./z3a2
// ------------1(3)
// ---------1(2)
// ------1(1)
// ---------0(2)
// ------------0(3)
// ---------------0(4)
// ---/(0)
// ---------1(2)
// ------0(1)
// ---------0(2)
// depth = 4
// mean = 2.75
// var = 0.957427
// a laborvĂ©dĂ©shez majd ezt a tesztelĂ©st hasznĂˇljuk:
// http://

  /* Ez volt eddig a main, de most komplexebb kell, mert explicite bejĂ¶vĹ‘, kimenĹ‘ fĂˇjlokkal kell dolgozni
int
main ()
{
    char b;
    LZWBinFa binFa;

    while (std::cin >> b)
    {
        binFa << b;
    }

    //std::cout << binFa.kiir (); // Ă­gy rajzolt ki a fĂˇt a korĂˇbbi verziĂłkban de, hogy izgalmasabb legyen
    // a pĂ©lda, azaz ki lehessen tolni az LZWBinFa-t kimeneti csatornĂˇra:

    std::cout << binFa; // ehhez kell a globĂˇlis operator<< tĂşlterhelĂ©se, lĂˇsd fentebb

    std::cout << "depth = " << binFa.getMelyseg () << std::endl;
    std::cout << "mean = " << binFa.getAtlag () << std::endl;
    std::cout << "var = " << binFa.getSzoras () << std::endl;

    binFa.szabadit ();

    return 0;
}
*/

  /* A parancssor arg. kezelĂ©st egyszerĹ±en bedolgozzuk a 2. hullĂˇm kapcsolĂłdĂł feladatĂˇbĂłl:
 http://progpater.blog.hu/2011/03/12/hey_mikey_he_likes_it_ready_for_more_3
 de mivel nekĂĽnk sokkal egyszerĹ±bb is elĂ©g, alig hagyunk meg belĹ‘le valamit...
 */
  /*
void
usage (void)
{
  std::cout << "Usage: lzwtree in_file -o out_file" << std::endl;
}
*/
  public static void usage() {
    System.out.println("Usage: lzwtree in_file -o out_file");
  }

  /*
int
main (int argc, char *argv[])
{
*/
  public static void main(String args[]) {
    // http://progpater.blog.hu/2011/03/12/hey_mikey_he_likes_it_ready_for_more_3
    // alapjĂˇn a parancssor argok ottani elegĂˇns feldolgozĂˇsĂˇbĂłl kb. ennyi marad:
    // "*((*++argv)+1)"...

    // a kiĂ­rĂˇs szerint ./lzwtree in_file -o out_file alakra kell mennie, ez 4 db arg:
  /*
     * if (argc != 4) { // ha nem annyit kapott a program, akkor
     * felhomĂˇlyosĂ­tjuk errĹ‘l a jĂşzetr: usage (); // Ă©s jelezzĂĽk az operĂˇciĂłs
     * rendszer felĂ©, hogy valami gĂˇz volt... return -1; }
     */
    if (args.length != 3) {
      // ha nem annyit kapott a program, akkor felhomĂˇlyosĂ­tjuk errĹ‘l a jĂşzetr:
      usage();
      // Ă©s jelezzĂĽk az operĂˇciĂłs rendszer felĂ©, hogy valami gĂˇz volt...
      System.exit(-1);
    }
    // "MegjegyezzĂĽk" a bemenĹ‘ fĂˇjl nevĂ©t
  /*
     * char *inFile = *++argv;
     */
    String inFile = args[0];
    // a -o kapcsolĂł jĂ¶n?
  /*
     * if (*((*++argv) + 1) != 'o') { usage (); return -2; }
     *
     */
    if (!"-o".equals(args[1])) {
      usage();
      System.exit(-1);
    }

    // ha igen, akkor az 5. elĹ‘adĂˇsbĂłl kimĂˇsoljuk a fĂˇjlkezelĂ©s C++ vĂˇltozatĂˇt:
/*
     * std::fstream beFile (inFile, std::ios_base::in);
     */
    try {

      java.io.FileInputStream beFile =
              new java.io.FileInputStream(new java.io.File(args[0]));
      // fejlesztgetjĂĽk a forrĂˇst: http://progpater.blog.hu/2011/04/17/a_tizedik_tizenegyedik_labor
              /*
       * if (!beFile) { std::cout << inFile << " nem letezik..." << std::endl;
       * usage (); return -3; }
       */ /*
       * std::fstream kiFile (*++argv, std::ios_base::out);
       */
      java.io.PrintWriter kiFile =
              new java.io.PrintWriter(
              new java.io.BufferedWriter(
              new java.io.FileWriter(args[2])));

      /*
       * unsigned char b;	// ide olvassik majd a bejĂ¶vĹ‘ fĂˇjl bĂˇjtjait
       *
       */
      byte[] b = new byte[1];
      /*
       * LZWBinFa binFa;	// s nyomjuk majd be az LZW fa objektumunkba
       *
       */
      LZWBinFa binFa = new LZWBinFa();

      // a bemenetet binĂˇrisan olvassuk, de a kimenĹ‘ fĂˇjlt mĂˇr karakteresen Ă­rjuk, hogy meg tudjuk
      // majd nĂ©zni... :) l. az emlĂ­tett 5. ea. C -> C++ gyĂ¶kkettes ĂˇtĂ­rĂˇsi pĂ©ldĂˇit
/*
       * while (beFile.read ((char *) &b, sizeof (unsigned char))) if (b ==
       * 0x0a) break;
       */
      while (beFile.read(b) != -1) {
        if (b[0] == 0x0a) {
          break;
        }
      }
      /*
       * bool kommentben = false;
       *
       */
      boolean kommentben = false;

      /*
       * while (beFile.read ((char *) &b, sizeof (unsigned char))) {
       *
       * if (b == 0x3e) {	// > karakter kommentben = true; continue; }
       *
       * if (b == 0x0a) {	// Ăşjsor kommentben = false; continue; }
       *
       * if (kommentben) continue;
       *
       * if (b == 0x4e)	// N betĹ± continue;
       *
       * // egyszerĹ±en a korĂˇbbi d.c kĂłdjĂˇt bemĂˇsoljuk // laboron tĂ¶bbszĂ¶r
       * lerajzoltuk ezt a bit-tologatĂˇst: // a b-ben lĂ©vĹ‘ bĂˇjt bitjeit egyenkĂ©nt
       * megnĂ©zzĂĽk for (int i = 0; i < 8; ++i) { // maszkolunk eddig..., most mĂˇr
       * simĂˇn Ă­rjuk az if fejĂ©be a legmagasabb helyiĂ©rtĂ©kĹ± bit vizsgĂˇlatĂˇt //
       * csupa 0 lesz benne a vĂ©gĂ©n pedig a vizsgĂˇlt 0 vagy 1, az if megmondja
       * melyik: if (b & 0x80) // ha a vizsgĂˇlt bit 1, akkor az '1' betĹ±t nyomjuk
       * az LZW fa objektumunkba binFa << '1'; else // kĂĽlĂ¶nben meg a '0' betĹ±t:
       * binFa << '0'; b <<= 1; }
       *
       * }
       */
      while (beFile.read(b) != -1) {

        if (b[0] == 0x3e) {			// > karakter
          kommentben = true;
          continue;
        }

        if (b[0] == 0x0a) {			// Ăşjsor 
          kommentben = false;
          continue;
        }

        if (kommentben) {
          continue;
        }

        if (b[0] == 0x4e) // N betĹ±
        {
          continue;
        }

        // egyszerĹ±en a korĂˇbbi d.c kĂłdjĂˇt bemĂˇsoljuk
        // laboron tĂ¶bbszĂ¶r lerajzoltuk ezt a bit-tologatĂˇst: 
        // a b-ben lĂ©vĹ‘ bĂˇjt bitjeit egyenkĂ©nt megnĂ©zzĂĽk
        for (int i = 0; i < 8; ++i) {
          // maszkolunk eddig..., most mĂˇr simĂˇn Ă­rjuk az if fejĂ©be a legmagasabb helyiĂ©rtĂ©kĹ± bit vizsgĂˇlatĂˇt
          // csupa 0 lesz benne a vĂ©gĂ©n pedig a vizsgĂˇlt 0 vagy 1, az if megmondja melyik:
          if ((b[0] & 0x80) != 0) // ha a vizsgĂˇlt bit 1, akkor az '1' betĹ±t nyomjuk az LZW fa objektumunkba
          {
            binFa.egyBitFeldolg('1');
          } else // kĂĽlĂ¶nben meg a '0' betĹ±t:
          {
            binFa.egyBitFeldolg('0');
          }
          b[0] <<= 1;
        }

      }

      //std::cout << binFa.kiir (); // Ă­gy rajzolt ki a fĂˇt a korĂˇbbi verziĂłkban de, hogy izgalmasabb legyen
      // a pĂ©lda, azaz ki lehessen tolni az LZWBinFa-t kimeneti csatornĂˇra:
/*
       * kiFile << binFa;	// ehhez kell a globĂˇlis operator<< tĂşlterhelĂ©se, lĂˇsd
       * fentebb // (jĂł ez az OO, mert mi ugye nem igazĂˇn erre gondoltunk, amikor
       * Ă­rtuk, mĂ©gis megy, hurrĂˇ)
       */
      binFa.kiir(kiFile);

      /*
       * kiFile << "depth = " << binFa.getMelyseg () << std::endl; kiFile <<
       * "mean = " << binFa.getAtlag () << std::endl; kiFile << "var = " <<
       * binFa.getSzoras () << std::endl;
       */
      kiFile.println("depth = " + binFa.getMelyseg());
      kiFile.println("mean = " + binFa.getAtlag());
      kiFile.println("var = " + binFa.getSzoras());

      kiFile.close();
      beFile.close();

    } catch (java.io.FileNotFoundException fnfException) {
      fnfException.printStackTrace();
    } catch (java.io.IOException ioException) {
      ioException.printStackTrace();
    }

  }
}