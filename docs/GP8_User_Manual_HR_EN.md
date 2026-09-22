**Android application for timing five figure eights  
Android aplikacija za mjerenje pet osmici**

| **Document**   | **Value**                                                         |
|----------------|-------------------------------------------------------------------|
| Application    | GP8 Stopwatch version 1.0                                         |
| Primary device | Samsung Galaxy S25 or compatible Android phone with accelerometer |
| Purpose        | Practice timing for the 5 x figure eight GP8 exercise             |
| Languages      | Croatian and English                                              |
| Document date  | 22 September 2026                                                 |

**Important.** This application is a sensor-based practice aid. It is
not a certified timing system and does not replace official timing
equipment, an instructor, or safe riding procedures.

# Contents

| **Hrvatski dio**              | **English section**               |
|-------------------------------|-----------------------------------|
| 1 Namjena i sigurnost         | 1 Purpose and safety              |
| 2 Instalacija i priprema      | 2 Installation and preparation    |
| 3 Elementi i statusi sučelja  | 3 Interface controls and statuses |
| 4 Postupak mjerenja           | 4 Timing procedure                |
| 5 Rezultat i postavke senzora | 5 Results and sensor settings     |
| 6 Otklanjanje problema        | 6 Troubleshooting                 |
| 7 Brzi podsjetnik             | 7 Quick reference                 |

# Hrvatske korisničke upute

## 1 Namjena i sigurnost

GP8 Štoperica mjeri vrijeme izvođenja pet uzastopnih osmici. Telefon
koristi akcelerometar kako bi nakon kalibracije automatski prepoznao
polazak motocikla. Na završetku aplikacija pamti početak dovoljno dugog
mirovanja, a vozač mjerenje potvrđuje tipkom STOP.

**Sigurnost.** Telefon mora biti čvrsto pričvršćen prije pokretanja
motocikla. Ne podešavajte aplikaciju, ne mijenjajte orijentaciju
telefona i ne gledajte zaslon tijekom vožnje. Postavke mijenjajte samo
dok motocikl sigurno stoji.

- Aplikacija ne broji osmice i ne provjerava pravilnost vožnje.

- Mjerenje završava tek kada vozač pritisne STOP; ako je mirovanje već
  prepoznato, rezultat se vraća na početak tog mirovanja.

- Prvih 15 sekundi nakon automatskog starta aplikacija ne traži završno
  mirovanje.

- Rezultat ovisi o nosaču, telefonu, vibracijama motora, podlozi i
  postavkama.

## 2 Instalacija i priprema

### 2.1 Preduvjeti

- Android 8.0 ili noviji i ugrađeni akcelerometar.

- Čvrst nosač koji ne dopušta ljuljanje ili klizanje telefona.

- Dovoljno napunjena baterija i isključen način štednje koji bi mogao
  zatvoriti aplikaciju.

### 2.2 Instalacija APK datoteke

1.  Kopirajte APK datoteku na telefon i otvorite je u aplikaciji Moje
    datoteke ili drugom upravitelju datoteka.

2.  Ako Android to zatraži, privremeno dopustite instaliranje nepoznatih
    aplikacija za taj upravitelj datoteka.

3.  Dodirnite Instaliraj, pričekajte završetak i pokrenite GP8
    Štopericu.

4.  Nakon instalacije po želji ponovno isključite dopuštenje za
    nepoznate aplikacije.

**Ažuriranje.** Ako Android odbije instalirati novu razvojnu verziju
zbog različitog potpisa, prvo zabilježite postavke, deinstalirajte staru
verziju i zatim instalirajte novu. Deinstalacija briše spremljene
postavke aplikacije.

### 2.3 Priprema prije vožnje

5.  Postavite telefon čvrsto na isto mjesto i u istom položaju koji ćete
    koristiti za sva usporedna mjerenja.

6.  Odaberite portretni ili vodoravni prikaz prije pritiska na PRIPREMA.
    Promjena orijentacije tijekom mjerenja može prekinuti aktivno
    mjerenje.

7.  Pokrenite motor i ostavite motocikl stabilnim u leru na mjestu
    starta.

8.  Otvorite aplikaciju. Zaslon ostaje uključen dok je aplikacija
    aktivna.

## 3 Elementi i statusi sučelja

| **Element**           | **Značenje ili radnja**                                                                        |
|-----------------------|------------------------------------------------------------------------------------------------|
| Veliki prikaz vremena | Prikazuje tekuće ili konačno vrijeme u formatu minute:sekunde.tisućinke, primjerice 00:30.245. |
| PRIPREMA              | Pokreće kalibraciju i naoružava automatsko prepoznavanje polaska.                              |
| ODUSTANI              | Prekida kalibraciju ili aktivno mjerenje i vraća aplikaciju u stanje SPREMNO.                  |
| STOP                  | Potvrđuje završetak. Pojavljuje se tek nakon automatski prepoznatog polaska.                   |
| NOVA VOŽNJA           | Nakon rezultata priprema zaslon za novi pokušaj.                                               |
| POSTAVKE SENZORA      | Otvara prag polaska, prag mirovanja i trajanje potvrde mirovanja.                              |

| **Status**     | **Što znači**                                           | **Što učiniti**                                |
|----------------|---------------------------------------------------------|------------------------------------------------|
| SPREMNO        | Aplikacija ne mjeri.                                    | Pritisnite PRIPREMA kada ste na startu.        |
| KALIBRACIJA    | Mjeri normalne vibracije motocikla približno 2 sekunde. | Ne dirajte motocikl ni upravljač.              |
| ČEKA POLAZAK   | Kalibracija je završena.                                | Krenite kada ste spremni.                      |
| MJERENJE       | Polazak je potvrđen i vrijeme teče.                     | Izvedite pet osmici i potpuno se zaustavite.   |
| CILJ PREPOZNAT | Dovoljno dugo mirovanje je zapamćeno.                   | Dok mirujete pritisnite STOP.                  |
| CILJ           | Rezultat je zaključen.                                  | Pročitajte rezultat ili odaberite NOVA VOŽNJA. |

**Prikaz na manjim ekranima.** Sučelje smanjuje razmake u vodoravnom
položaju i na nižim zaslonima. Ako sve kontrole nisu istodobno vidljive,
povucite sadržaj gore ili dolje. Pomicanje zaslona obavljajte samo dok
motocikl stoji.

## 4 Postupak mjerenja

9.  Na mjestu starta ostavite motor u leru i motocikl potpuno miran.

10. Pritisnite PRIPREMA. Osjetit ćete kratku vibraciju telefona, a
    status će postati KALIBRACIJA.

11. Najmanje 2 sekunde ne dirajte telefon, upravljač ni motocikl. Nakon
    kalibracije pojavljuje se ČEKA POLAZAK.

12. Krenite normalno. Sustav mora prepoznati gibanje iznad praga
    približno 0,3 sekunde. Nakon potvrde status postaje MJERENJE,
    pojavljuje se crvena tipka STOP i telefon vibrira.

13. Izvedite pet osmici. Završno mirovanje neće se tražiti tijekom prvih
    15 sekundi vožnje.

14. Na cilju potpuno zaustavite motocikl i ostanite mirni. Kada se
    pojavi CILJ PREPOZNAT, trenutak početka tog mirovanja već je
    spremljen.

15. Dok i dalje mirujete pritisnite STOP. Veliki broj prikazuje GP8
    vrijeme do početka prepoznatog završnog mirovanja.

16. Za novi pokušaj pritisnite NOVA VOŽNJA, a zatim ponovno PRIPREMA i
    ponovite kalibraciju.

**Ako se CILJ PREPOZNAT ne pojavi.** Možete svejedno pritisnuti STOP. U
tom slučaju aplikacija koristi trenutak pritiska tipke kao završetak i
prikazuje poruku Mirovanje nije prepoznato.

## 5 Rezultat i postavke senzora

### 5.1 Kako čitati rezultat

| **Prikaz**                | **Tumačenje**                                                                                                          |
|---------------------------|------------------------------------------------------------------------------------------------------------------------|
| Veliki broj               | Glavni GP8 rezultat. Ako je mirovanje prepoznato, završava na početku tog mirovanja; inače završava pritiskom na STOP. |
| START do STOP tipka       | Ukupno vrijeme od automatskog starta do fizičkog pritiska tipke STOP.                                                  |
| Nakon zaustavljanja       | Razlika između glavnog rezultata i vremena pritiska tipke STOP.                                                        |
| Mirovanje nije prepoznato | Senzor nije pronašao prihvatljivo završno mirovanje; rezultat je uzet s tipke STOP.                                    |

Primjer: motocikl je počeo završno mirovanje na 30,000 s, a STOP je
pritisnut na 36,000 s. Glavni rezultat je 30,000 s, START do STOP tipka
je 36,000 s, a Nakon zaustavljanja je +6,000 s.

### 5.2 Postavke senzora

| **Postavka**         | **Početno** | **Raspon**   | **Učinak promjene**                                                                                 |
|----------------------|-------------|--------------|-----------------------------------------------------------------------------------------------------|
| Osjetljivost polaska | 0,45        | 0,10 do 1,00 | Niža vrijednost lakše pokreće mjerenje, ali povećava lažne startove. Viša traži izraženiji polazak. |
| Prag mirovanja       | 0,18        | 0,05 do 0,40 | Viša vrijednost lakše prihvaća vibracije kao mirovanje. Niža traži mirniji motocikl.                |
| Potvrda mirovanja    | 2 s         | 1 do 3 s     | Kraće brže potvrđuje cilj; dulje bolje odbacuje kratka prolazna smirenja.                           |

**Preporučeni način podešavanja.** Mijenjajte samo jednu postavku
odjednom i napravite najmanje tri probne vožnje u istim uvjetima.
Početne vrijednosti vratite tipkom POČETNE. SPREMI pohranjuje nove
vrijednosti, a ODUSTANI zatvara dijalog bez spremanja.

## 6 Otklanjanje problema

| **Problem**                     | **Vjerojatan uzrok**                                                                  | **Preporučena radnja**                                                                                       |
|---------------------------------|---------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| Ne prepoznaje polazak           | Prag polaska je previsok ili je polazak vrlo blag.                                    | Smanjite Osjetljivost polaska u malim koracima, primjerice 0,45 na 0,40, i ponovite test.                    |
| Pokreće se dok motocikl miruje  | Prag polaska je prenizak ili je kalibracija izvedena uz dodirivanje motocikla.        | Ponovite mirnu kalibraciju; po potrebi povisite prag polaska.                                                |
| Ne prepoznaje završno mirovanje | Vibracije su iznad praga mirovanja, motocikl se još pomiče ili potvrda traje predugo. | Potpuno stanite i ne pomičite upravljač. Zatim prag mirovanja povisite postupno, npr. 0,18 na 0,20 ili 0,22. |
| Prerano prepoznaje cilj         | Prag mirovanja je previsok ili je potvrda prekratka.                                  | Smanjite prag mirovanja ili povećajte potvrdu na 2 do 3 sekunde.                                             |
| Cilj je prepoznat pa poništen   | Motocikl se ponovno jasno pokrenuo najmanje oko 0,5 sekundi.                          | Nakon cilja ostanite potpuno mirni do pritiska na STOP.                                                      |
| Tipke se ne vide                | Mali zaslon, velik sistemski font ili vodoravni prikaz.                               | Povucite sadržaj prema gore. Postavite orijentaciju prije mjerenja.                                          |
| Mjerenje se prekinulo           | Aplikacija je napuštena, zaslon je rotiran ili ju je sustav stavio u pozadinu.        | Ostanite u aplikaciji tijekom pokušaja i ne mijenjajte orijentaciju.                                         |
| Akcelerometar nije dostupan     | Telefon nema podržani senzor ili mu sustav ne daje podatke.                           | Koristite drugi kompatibilni telefon; mjerenje bez senzora nije moguće.                                      |

## 7 Brzi podsjetnik

<table>
<colgroup>
<col style="width: 50%" />
<col style="width: 50%" />
</colgroup>
<thead>
<tr class="header">
<th><strong>Prije vožnje</strong></th>
<th><strong>Na cilju</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>1 Čvrsto montiraj telefon<br />
2 Odaberi orijentaciju<br />
3 Motor u leru i motocikl miran<br />
4 Pritisni PRIPREMA<br />
5 Ne diraj 2 sekunde<br />
6 Kreni nakon ČEKA POLAZAK</td>
<td>1 Potpuno se zaustavi<br />
2 Ostani miran<br />
3 Pričekaj CILJ PREPOZNAT<br />
4 Pritisni STOP<br />
5 Pročitaj veliki rezultat<br />
6 Odaberi NOVA VOŽNJA</td>
</tr>
</tbody>
</table>

# English user instructions

## 1 Purpose and safety

GP8 Stopwatch measures the time required to complete five consecutive
figure eights. After calibration, the phone uses its accelerometer to
detect motorcycle movement and start the timer automatically. At the
finish, the application stores the beginning of a sufficiently long
stationary period, and the rider confirms the result by pressing STOP.

**Safety.** Secure the phone before starting the motorcycle. Do not
adjust the application, rotate the phone, or watch the screen while
riding. Change settings only while the motorcycle is safely stationary.

- The application does not count figure eights or verify riding
  accuracy.

- Timing is completed when the rider presses STOP. If the stationary
  period has already been detected, the displayed result is calculated
  back to its beginning.

- The application does not look for the final stationary period during
  the first 15 seconds after the automatic start.

- Results depend on the mount, phone, engine vibration, surface, and
  sensor settings.

## 2 Installation and preparation

### 2.1 Requirements

- Android 8.0 or later and a built-in accelerometer.

- A rigid mount that prevents the phone from rocking or sliding.

- Sufficient battery charge and no power-saving mode that may close the
  application.

### 2.2 Installing the APK

17. Copy the APK file to the phone and open it in My Files or another
    file manager.

18. If Android asks, temporarily allow that file manager to install
    unknown applications.

19. Tap Install, wait for completion, and open GP8 Stopwatch.

20. After installation, you may disable the unknown-app permission
    again.

**Updating.** If Android refuses to install a development build because
its signature differs, record your settings, uninstall the old version,
and install the new one. Uninstalling removes saved application
settings.

### 2.3 Preparing for a run

21. Mount the phone firmly in the same location and orientation for
    every comparable run.

22. Select portrait or landscape before pressing PRIPREMA. Rotating the
    phone during timing may interrupt the active run.

23. Start the engine and keep the motorcycle stable at idle at the start
    position.

24. Open the application. The display remains awake while the
    application is active.

## 3 Interface controls and statuses

The current application interface is in Croatian. The following tables
translate every control and status needed during operation.

| **Screen label** | **English meaning and action**                                                                  |
|------------------|-------------------------------------------------------------------------------------------------|
| PRIPREMA         | PREPARE. Starts calibration and arms automatic start detection.                                 |
| ODUSTANI         | CANCEL. Cancels calibration or an active run and returns to ready state.                        |
| STOP             | Confirms the finish. It appears only after movement has started.                                |
| NOVA VOŽNJA      | NEW RUN. Clears the result display and prepares for another attempt.                            |
| POSTAVKE SENZORA | SENSOR SETTINGS. Opens start threshold, stationary threshold, and stationary confirmation time. |

| **Croatian status** | **English meaning**                               | **Required action**                          |
|---------------------|---------------------------------------------------|----------------------------------------------|
| SPREMNO             | READY; not timing.                                | Press PRIPREMA at the start.                 |
| KALIBRACIJA         | CALIBRATING normal vibration for about 2 seconds. | Do not touch the motorcycle or handlebars.   |
| ČEKA POLAZAK        | WAITING FOR START; calibration is complete.       | Begin when ready.                            |
| MJERENJE            | TIMING; movement has been confirmed.              | Ride five figure eights and stop completely. |
| CILJ PREPOZNAT      | FINISH DETECTED; stationary start time is stored. | Remain still and press STOP.                 |
| CILJ                | FINISH; the result is locked.                     | Read the result or select NOVA VOŽNJA.       |

**Small displays.** The interface reduces spacing in landscape
orientation and on shorter displays. If all controls are not visible at
once, scroll up or down. Scroll only while the motorcycle is stationary.

## 4 Timing procedure

25. At the start position, leave the engine idling and keep the
    motorcycle fully stationary.

26. Press PRIPREMA. The phone gives a short vibration and the status
    changes to KALIBRACIJA.

27. For at least 2 seconds, do not touch the phone, handlebars, or
    motorcycle. When calibration is complete, ČEKA POLAZAK appears.

28. Move off normally. Movement must remain above the start threshold
    for approximately 0.3 seconds. When confirmed, the status becomes
    MJERENJE, the red STOP button appears, and the phone vibrates.

29. Complete five figure eights. Final stationary detection is disabled
    during the first 15 seconds of the run.

30. At the finish, stop completely and remain still. When CILJ PREPOZNAT
    appears, the beginning of that stationary period has already been
    stored.

31. While still stationary, press STOP. The large number is the GP8 time
    ending at the beginning of the detected final stationary period.

32. For another attempt, press NOVA VOŽNJA, then PRIPREMA, and repeat
    calibration.

**If CILJ PREPOZNAT does not appear.** You may still press STOP. The
application then uses the button press as the finish and displays
Mirovanje nije prepoznato, meaning stationary state was not detected.

## 5 Results and sensor settings

### 5.1 Reading the result

| **Displayed item**        | **Meaning**                                                                                                                           |
|---------------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| Large time                | Main GP8 result. It ends at the beginning of detected final stationary time, or at the STOP press if no stationary time was detected. |
| START → STOP tipka        | Total time from automatic start to the physical STOP button press.                                                                    |
| Nakon zaustavljanja       | Time between the main result and the STOP button press; literally After stopping.                                                     |
| Mirovanje nije prepoznato | Stationary state was not detected; the STOP button time is used as the result.                                                        |

Example: the motorcycle became stationary at 30.000 s and STOP was
pressed at 36.000 s. The main result is 30.000 s, START to STOP is
36.000 s, and After stopping is +6.000 s.

### 5.2 Sensor settings

<table>
<colgroup>
<col style="width: 25%" />
<col style="width: 25%" />
<col style="width: 25%" />
<col style="width: 25%" />
</colgroup>
<thead>
<tr class="header">
<th><strong>Setting</strong></th>
<th><strong>Default</strong></th>
<th><strong>Range</strong></th>
<th><strong>Effect</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>Osjetljivost polaska<br />
Start threshold</td>
<td>0.45</td>
<td>0.10 to 1.00</td>
<td>A lower value starts more easily but increases false starts. A
higher value requires stronger movement.</td>
</tr>
<tr class="even">
<td>Prag mirovanja<br />
Stationary threshold</td>
<td>0.18</td>
<td>0.05 to 0.40</td>
<td>A higher value accepts more vibration as stationary. A lower value
requires a quieter motorcycle.</td>
</tr>
<tr class="odd">
<td>Potvrda mirovanja<br />
Stationary confirmation</td>
<td>2 s</td>
<td>1 to 3 s</td>
<td>A shorter time confirms sooner. A longer time rejects brief quiet
periods more reliably.</td>
</tr>
</tbody>
</table>

**Recommended tuning method.** Change only one setting at a time and
complete at least three test runs under the same conditions. POČETNE
restores defaults, SPREMI saves changes, and ODUSTANI closes the dialog
without saving.

## 6 Troubleshooting

| **Problem**                   | **Likely cause**                                                                             | **Recommended action**                                                                                                          |
|-------------------------------|----------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
| Start is not detected         | Start threshold is too high or movement is very gentle.                                      | Reduce the start threshold in small steps, for example from 0.45 to 0.40, then retest.                                          |
| Timer starts while stationary | Start threshold is too low or the motorcycle was touched during calibration.                 | Repeat calibration without touching the motorcycle; increase the start threshold if necessary.                                  |
| Final stop is not detected    | Vibration exceeds the stationary threshold, movement continues, or confirmation is too long. | Stop completely and avoid moving the handlebars. Gradually increase the stationary threshold, for example 0.18 to 0.20 or 0.22. |
| Finish is detected too early  | Stationary threshold is too high or confirmation is too short.                               | Lower the stationary threshold or set confirmation to 2 or 3 seconds.                                                           |
| Detected finish is cancelled  | The motorcycle clearly moved again for about 0.5 seconds or longer.                          | Remain fully stationary until STOP is pressed.                                                                                  |
| Buttons are not visible       | Small display, large system font, or landscape orientation.                                  | Scroll upward. Select orientation before timing.                                                                                |
| Timing was interrupted        | The application was left, the phone was rotated, or Android moved it to the background.      | Keep the application in the foreground and do not rotate the phone during a run.                                                |
| Accelerometer unavailable     | No supported sensor data is available.                                                       | Use another compatible phone; sensor timing cannot operate without an accelerometer.                                            |

## 7 Quick reference

<table>
<colgroup>
<col style="width: 50%" />
<col style="width: 50%" />
</colgroup>
<thead>
<tr class="header">
<th><strong>Before the run</strong></th>
<th><strong>At the finish</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>1 Secure the phone<br />
2 Select orientation<br />
3 Engine idle and motorcycle still<br />
4 Press PRIPREMA<br />
5 Do not touch for 2 seconds<br />
6 Start after ČEKA POLAZAK</td>
<td>1 Stop completely<br />
2 Remain still<br />
3 Wait for CILJ PREPOZNAT<br />
4 Press STOP<br />
5 Read the large result<br />
6 Select NOVA VOŽNJA</td>
</tr>
</tbody>
</table>
