package com.example.department_APP.database

import com.example.department_APP.models.Department
import com.example.department_APP.models.Faculty

// Initial content of the database.
// Source: UMBB 2026 university guides and faculty websites. Check before publishing.
// If you edit this file, uninstall the app (or clear its data) to reload it.
object DatabaseSeeder {

    fun seedIfEmpty(helper: DatabaseHelper) {
        if (helper.getAllFaculties().isNotEmpty()) return

        // A transaction: either everything is inserted, or nothing
        val db = helper.writableDatabase
        db.beginTransaction()
        try {
            addSciences(helper)
            addTechnology(helper)
            addHydrocarbons(helper)
            addEconomics(helper)
            addLaw(helper)
            addLetters(helper)
            addMedicine(helper)
            addIgee(helper)
            addIsta(helper)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    private fun addSciences(helper: DatabaseHelper) {
        val facultyId = helper.insertFaculty(
            Faculty(
                nameEn = "Faculty of Sciences",
                nameAr = "كلية العلوم",
                descriptionEn = "Mathematics, computer science, physics, chemistry, biology, agronomy and sport sciences. South Campus.",
                descriptionAr = "الرياضيات والإعلام الآلي والفيزياء والكيمياء والبيولوجيا والفلاحة وعلوم الرياضة. القطب الجنوبي.",
                address = "South Campus, Route de la Gare, 35000 Boumerdes",
                phone = "+21324799007",
                email = null,
                latitude = null,
                longitude = null
            )
        ).toInt()

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Computer Science",
                nameAr = "الإعلام الآلي",
                descriptionEn = "Department of Computer Science, Faculty of Sciences.",
                descriptionAr = "قسم الإعلام الآلي، كلية العلوم.",
                specialties = "Bachelor: Information Systems and Software Engineering, Computer Systems, Web Development and Computer Graphics\nMaster: Software Engineering and Data Processing, Distributed Information Systems, Information Technology, Applied AI",
                phone = "+21324799009",
                specialtiesAr = "ليسانس: نظم المعلومات وهندسة البرمجيات، الأنظمة المعلوماتية، تطوير الويب والرسم الحاسوبي\nماستر: هندسة البرمجيات ومعالجة البيانات، نظم المعلومات الموزعة، تكنولوجيا المعلومات، الذكاء الاصطناعي التطبيقي"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Mathematics",
                nameAr = "الرياضيات",
                descriptionEn = "Department of Mathematics, Faculty of Sciences.",
                descriptionAr = "قسم الرياضيات، كلية العلوم.",
                specialties = "Bachelor: Mathematics, Applied Mathematics, Data Science\nMaster: Mathematical Analysis, Financial Mathematics, Stochastic and Statistical Modeling, Operational Research, Cryptography and Cyber Security",
                phone = "+21324799010",
                specialtiesAr = "ليسانس: الرياضيات، الرياضيات التطبيقية، علم البيانات\nماستر: التحليل الرياضي، الرياضيات المالية، النمذجة العشوائية والإحصائية، بحوث العمليات، التشفير والأمن السيبراني"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Physics",
                nameAr = "الفيزياء",
                descriptionEn = "Department of Physics, Faculty of Sciences.",
                descriptionAr = "قسم الفيزياء، كلية العلوم.",
                specialties = "Bachelor: Material, Radiation, Energetic and Fundamental Physics\nMaster: Materials Physics, Energy Physics and Renewable Energies, Medical Physics, Fluid Dynamics and Energetics",
                phone = "+21324799017",
                specialtiesAr = "ليسانس: فيزياء المواد، فيزياء الإشعاع، الفيزياء الطاقوية، الفيزياء الأساسية\nماستر: فيزياء المواد، الفيزياء الطاقوية والطاقات المتجددة، الفيزياء الطبية، ديناميك الموائع والطاقوية"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Chemistry",
                nameAr = "الكيمياء",
                descriptionEn = "Department of Chemistry, Faculty of Sciences.",
                descriptionAr = "قسم الكيمياء، كلية العلوم.",
                specialties = "Bachelor: Materials, Organic and Analytical Chemistry\nMaster: Materials, Pharmaceutical, Environmental, Analytical and Organic Chemistry",
                phone = "+21324799024",
                specialtiesAr = "ليسانس: كيمياء المواد، الكيمياء العضوية، الكيمياء التحليلية\nماستر: كيمياء المواد، الكيمياء الصيدلانية، كيمياء البيئة، الكيمياء التحليلية، الكيمياء العضوية"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Biology",
                nameAr = "البيولوجيا",
                descriptionEn = "Department of Biology, Faculty of Sciences.",
                descriptionAr = "قسم البيولوجيا، كلية العلوم.",
                specialties = "Bachelor: Biochemistry, Biology of Organisms, Biology and Animal Physiology, Molecular Biology\nMaster: Applied Biochemistry, Populations and Organisms, Genetics, Cell Physiology and Physiopathology, Molecular and Cellular Biology",
                phone = "+21324799018",
                specialtiesAr = "ليسانس: الكيمياء الحيوية، بيولوجيا الكائنات، بيولوجيا وفيزيولوجيا الحيوان، البيولوجيا الجزيئية\nماستر: الكيمياء الحيوية التطبيقية، المجموعات والكائنات، علم الوراثة، فيزيولوجيا الخلية والفيزيولوجيا المرضية، البيولوجيا الجزيئية والخلوية"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Agronomy",
                nameAr = "الفلاحة",
                descriptionEn = "Department of Agronomy, Faculty of Sciences.",
                descriptionAr = "قسم الفلاحة، كلية العلوم.",
                specialties = "Bachelor: Plant Protection, Plant Production, Animal Production, Agroecology\nMaster: Phytopharmacy and Plant Protection, Animal Production and Nutrition, Plant Production, Phytopathology, Agro-environment and Bio-indicators",
                phone = "+21324799025",
                specialtiesAr = "ليسانس: حماية النباتات، الإنتاج النباتي، الإنتاج الحيواني، الزراعة الإيكولوجية\nماستر: الصيدلة النباتية وحماية النباتات، الإنتاج والتغذية الحيوانية، الإنتاج النباتي، أمراض النبات، البيئة الفلاحية والمؤشرات الحيوية"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Physical and Sports Activities (STAPS)",
                nameAr = "علوم وتقنيات النشاطات البدنية والرياضية",
                descriptionEn = "Department of Physical and Sports Activities (STAPS), Faculty of Sciences.",
                descriptionAr = "قسم علوم وتقنيات النشاطات البدنية والرياضية، كلية العلوم.",
                specialties = "Bachelor: Education and Motricity, Competitive Sports Training\nMaster: School Physical and Sports Activity, Elite Sports Training",
                phone = "+21324799033",
                specialtiesAr = "ليسانس: التربية وعلم الحركة، التدريب الرياضي التنافسي\nماستر: النشاط البدني الرياضي المدرسي، تدريب رياضيي النخبة"
            )
        )
    }

    private fun addTechnology(helper: DatabaseHelper) {
        val facultyId = helper.insertFaculty(
            Faculty(
                nameEn = "Faculty of Technology",
                nameAr = "كلية التكنولوجيا",
                descriptionEn = "Mechanical, civil, electrical and process engineering. West Campus.",
                descriptionAr = "الهندسة الميكانيكية والمدنية والكهربائية وهندسة الطرائق. القطب الغربي.",
                address = "West Campus, Cite Frantz Fanon, 35000 Boumerdes",
                phone = "+21324941499",
                email = "contactft@univ-boumerdes.dz",
                latitude = null,
                longitude = null
            )
        ).toInt()

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Mechanical Engineering",
                nameAr = "الهندسة الميكانيكية",
                descriptionEn = "Department of Mechanical Engineering, Faculty of Technology.",
                descriptionAr = "قسم الهندسة الميكانيكية، كلية التكنولوجيا.",
                specialties = "Bachelor: Mechanical Construction, Energetics, Materials Engineering, Automotive Engineering\nMaster: Mechanical Construction, Energetics, Mechanical and Production Manufacturing, Materials Engineering, Energy Installations and Turbomachinery, Renewable Energies in Mechanics",
                phone = "+21324941479",
                specialtiesAr = "ليسانس: الإنشاء الميكانيكي، الطاقوية، هندسة المواد، هندسة السيارات\nماستر: الإنشاء الميكانيكي، الطاقوية، التصنيع الميكانيكي والإنتاج، هندسة المواد، المنشآت الطاقوية والآلات التوربينية، الطاقات المتجددة في الميكانيك"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Civil Engineering",
                nameAr = "الهندسة المدنية",
                descriptionEn = "Department of Civil Engineering, Faculty of Technology.",
                descriptionAr = "قسم الهندسة المدنية، كلية التكنولوجيا.",
                specialties = "Bachelor: Civil Engineering\nMaster: Geotechnics, Materials in Civil Engineering, Structures, Structures and Buildings",
                phone = "+21324941504",
                specialtiesAr = "ليسانس: الهندسة المدنية\nماستر: الجيوتقني، مواد الهندسة المدنية، الهياكل، الهياكل والبنايات"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Electrical Systems Engineering",
                nameAr = "هندسة الأنظمة الكهربائية",
                descriptionEn = "Department of Electrical Systems Engineering, Faculty of Technology.",
                descriptionAr = "قسم هندسة الأنظمة الكهربائية، كلية التكنولوجيا.",
                specialties = "Bachelor: Automatic Control, Electronics, Electrotechnics\nMaster: Automation and Industrial Computing, Electronics of Embedded Systems, Electrical Machines",
                phone = "+21324941488",
                specialtiesAr = "ليسانس: الآلية، الإلكترونيك، الإلكتروتقني\nماستر: الآلية والإعلام الآلي الصناعي، إلكترونيك الأنظمة المدمجة، الآلات الكهربائية"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Process Engineering",
                nameAr = "هندسة الطرائق",
                descriptionEn = "Department of Process Engineering, Faculty of Technology.",
                descriptionAr = "قسم هندسة الطرائق، كلية التكنولوجيا.",
                specialties = "Bachelor: Process Engineering\nMaster: Food, Chemical, Polymer and Materials Process Engineering, Environmental Engineering, Water Engineering and Management",
                phone = "+21324941485",
                specialtiesAr = "ليسانس: هندسة الطرائق\nماستر: هندسة الأغذية، الهندسة الكيميائية، هندسة البوليمرات، هندسة طرائق المواد، هندسة البيئة، هندسة وتسيير المياه"
            )
        )
    }

    private fun addHydrocarbons(helper: DatabaseHelper) {
        val facultyId = helper.insertFaculty(
            Faculty(
                nameEn = "Faculty of Hydrocarbons and Chemistry",
                nameAr = "كلية المحروقات والكيمياء",
                descriptionEn = "Training in oil, gas, petrochemistry, geosciences and hydrocarbon economics. North Campus.",
                descriptionAr = "تكوين في البترول والغاز والبتروكيمياء وعلوم الأرض واقتصاد المحروقات. القطب الشمالي.",
                address = "North Campus, Avenue de l'Independance, 35000 Boumerdes",
                phone = "+21324795254",
                email = "fhc@univ-boumerdes.dz",
                latitude = null,
                longitude = null
            )
        ).toInt()

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Earthquake Engineering and Random Phenomena",
                nameAr = "هندسة الزلازل والظواهر العشوائية",
                descriptionEn = "Department of Earthquake Engineering and Random Phenomena, Faculty of Hydrocarbons and Chemistry.",
                descriptionAr = "قسم هندسة الزلازل والظواهر العشوائية، كلية المحروقات والكيمياء.",
                specialties = null,
                phone = "+21324795267",
                specialtiesAr = null
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Hydrocarbon Transport and Equipment",
                nameAr = "نقل وعتاد المحروقات",
                descriptionEn = "Department of Hydrocarbon Transport and Equipment, Faculty of Hydrocarbons and Chemistry.",
                descriptionAr = "قسم نقل وعتاد المحروقات، كلية المحروقات والكيمياء.",
                specialties = "Bachelor: Mechanical Engineering (Hydrocarbon Transport, Petrochemical Units, Oilfield Mechanics)\nMaster: Transport and Distribution of Hydrocarbons, Oilfield Mechanics, Mechanics of Petrochemical Units",
                phone = "+21324795166",
                specialtiesAr = "ليسانس: الهندسة الميكانيكية (نقل المحروقات، الوحدات البتروكيميائية، ميكانيك حقول البترول)\nماستر: نقل وتوزيع المحروقات، ميكانيك حقول البترول، ميكانيك الوحدات البتروكيميائية"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Chemical and Pharmaceutical Process Engineering",
                nameAr = "هندسة الطرائق الكيميائية والصيدلانية",
                descriptionEn = "Department of Chemical and Pharmaceutical Process Engineering, Faculty of Hydrocarbons and Chemistry.",
                descriptionAr = "قسم هندسة الطرائق الكيميائية والصيدلانية، كلية المحروقات والكيمياء.",
                specialties = "Bachelor: Process Engineering, Oil and Gas Processing Technologies\nMaster: Refining, Petrochemical Technology, Health Safety Environment",
                phone = "+21324795265",
                specialtiesAr = "ليسانس: هندسة الطرائق، تكنولوجيا معالجة البترول والغاز\nماستر: التكرير، التكنولوجيا البتروكيميائية، الصحة والأمن والبيئة"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Process Automation and Electrification",
                nameAr = "أتمتة وكهربة الطرائق",
                descriptionEn = "Department of Process Automation and Electrification, Faculty of Hydrocarbons and Chemistry.",
                descriptionAr = "قسم أتمتة وكهربة الطرائق، كلية المحروقات والكيمياء.",
                specialties = "Bachelor: Automation of Industrial Processes, Industrial Electricity\nMaster: Automatic Control, Instrumentation in the Petrochemical Industry, Industrial Electricity",
                phone = "+21324795170",
                specialtiesAr = "ليسانس: آلية الطرائق الصناعية، الكهرباء الصناعية\nماستر: التحكم الآلي، الأجهزة في الصناعة البتروكيميائية، الكهرباء الصناعية"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Mining and Petroleum Deposits",
                nameAr = "حقول المعادن والمحروقات",
                descriptionEn = "Department of Mining and Petroleum Deposits, Faculty of Hydrocarbons and Chemistry.",
                descriptionAr = "قسم حقول المعادن والمحروقات، كلية المحروقات والكيمياء.",
                specialties = "Bachelor: Applied Geosciences, Geophysics, Petroleum Engineering (Production, Drilling)\nMaster: Petroleum Geology, Petroleum Geophysics, Petroleum Engineering (Production, Drilling)",
                phone = "+21324795164",
                specialtiesAr = "ليسانس: علوم الأرض التطبيقية، الجيوفيزياء، الهندسة البترولية (الإنتاج، الحفر)\nماستر: جيولوجيا البترول، جيوفيزياء البترول، الهندسة البترولية (الإنتاج، الحفر)"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Hydrocarbon Economics and Marketing",
                nameAr = "اقتصاد وتسويق المحروقات",
                descriptionEn = "Department of Hydrocarbon Economics and Marketing, Faculty of Hydrocarbons and Chemistry.",
                descriptionAr = "قسم اقتصاد وتسويق المحروقات، كلية المحروقات والكيمياء.",
                specialties = "Bachelor: Petroleum Economics\nMaster: Hydrocarbon Economics",
                phone = "+21324795208",
                specialtiesAr = "ليسانس: اقتصاد البترول\nماستر: اقتصاد المحروقات"
            )
        )
    }

    private fun addEconomics(helper: DatabaseHelper) {
        val facultyId = helper.insertFaculty(
            Faculty(
                nameEn = "Faculty of Economics, Commercial and Management Sciences",
                nameAr = "كلية العلوم الاقتصادية والتجارية وعلوم التسيير",
                descriptionEn = "Economics, commerce, management, finance and accounting. North Campus.",
                descriptionAr = "الاقتصاد والتجارة والتسيير والمالية والمحاسبة. القطب الشمالي.",
                address = "North Campus, Avenue de l'Independance, 35000 Boumerdes",
                phone = "+21324795194",
                email = null,
                latitude = null,
                longitude = null
            )
        ).toInt()

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Management Sciences",
                nameAr = "علوم التسيير",
                descriptionEn = "Department of Management Sciences, Faculty of Economics, Commercial and Management Sciences.",
                descriptionAr = "قسم علوم التسيير، كلية العلوم الاقتصادية والتجارية وعلوم التسيير.",
                specialties = "Bachelor: Entrepreneurship, Management, Financial Management\nMaster: Management, Public Management, Financial Management",
                phone = "+21324795143",
                specialtiesAr = "ليسانس: ريادة الأعمال، إدارة الأعمال، الإدارة المالية\nماستر: إدارة الأعمال، الإدارة العمومية، الإدارة المالية"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Commercial Sciences",
                nameAr = "العلوم التجارية",
                descriptionEn = "Department of Commercial Sciences, Faculty of Economics, Commercial and Management Sciences.",
                descriptionAr = "قسم العلوم التجارية، كلية العلوم الاقتصادية والتجارية وعلوم التسيير.",
                specialties = "Bachelor: Marketing, Digital Marketing, Finance and International Trade\nMaster: Finance and International Trade, Service Marketing, Digital Marketing",
                phone = "+21324795144",
                specialtiesAr = "ليسانس: التسويق، التسويق الرقمي، المالية والتجارة الدولية\nماستر: المالية والتجارة الدولية، تسويق الخدمات، التسويق الرقمي"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Economics",
                nameAr = "العلوم الاقتصادية",
                descriptionEn = "Department of Economics, Faculty of Economics, Commercial and Management Sciences.",
                descriptionAr = "قسم العلوم الاقتصادية، كلية العلوم الاقتصادية والتجارية وعلوم التسيير.",
                specialties = "Bachelor: Quantitative Economics, Monetary and Financial Economics, Economics and Business Management",
                phone = "+21324795191",
                specialtiesAr = "ليسانس: الاقتصاد الكمي، الاقتصاد النقدي والمالي، اقتصاد وتسيير المؤسسات"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Finance and Accounting",
                nameAr = "المالية والمحاسبة",
                descriptionEn = "Department of Finance and Accounting, Faculty of Economics, Commercial and Management Sciences.",
                descriptionAr = "قسم المالية والمحاسبة، كلية العلوم الاقتصادية والتجارية وعلوم التسيير.",
                specialties = "Bachelor: Accounting, Finance",
                phone = "+21324795257",
                specialtiesAr = "ليسانس: المحاسبة، المالية"
            )
        )
    }

    private fun addLaw(helper: DatabaseHelper) {
        val facultyId = helper.insertFaculty(
            Faculty(
                nameEn = "Faculty of Law and Political Science",
                nameAr = "كلية الحقوق والعلوم السياسية",
                descriptionEn = "Public law, private law and political science. Boudouaou Campus.",
                descriptionAr = "القانون العام والقانون الخاص والعلوم السياسية. قطب بودواو.",
                address = "Boudouaou Campus, Boudouaou, Boumerdes",
                phone = "+21324745511",
                email = null,
                latitude = null,
                longitude = null
            )
        ).toInt()

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Public Law",
                nameAr = "القانون العام",
                descriptionEn = "Department of Public Law, Faculty of Law and Political Science.",
                descriptionAr = "قسم القانون العام، كلية الحقوق والعلوم السياسية.",
                specialties = "Bachelor: Public Law\nMaster: Public Law",
                phone = "+21324745513",
                specialtiesAr = "ليسانس: القانون العام\nماستر: القانون العام"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Private Law",
                nameAr = "القانون الخاص",
                descriptionEn = "Department of Private Law, Faculty of Law and Political Science.",
                descriptionAr = "قسم القانون الخاص، كلية الحقوق والعلوم السياسية.",
                specialties = "Bachelor: Private Law\nMaster: Private Law, Business Law",
                phone = "+21324745516",
                specialtiesAr = "ليسانس: القانون الخاص\nماستر: القانون الخاص، قانون الأعمال"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Political Science",
                nameAr = "العلوم السياسية",
                descriptionEn = "Department of Political Science, Faculty of Law and Political Science.",
                descriptionAr = "قسم العلوم السياسية، كلية الحقوق والعلوم السياسية.",
                specialties = "Bachelor: International Relations, Political and Administrative Organizations\nMaster: Local Administration, International Cooperation",
                phone = "+21324745515",
                specialtiesAr = "ليسانس: العلاقات الدولية، التنظيمات السياسية والإدارية\nماستر: الإدارة المحلية، التعاون الدولي"
            )
        )
    }

    private fun addLetters(helper: DatabaseHelper) {
        val facultyId = helper.insertFaculty(
            Faculty(
                nameEn = "Faculty of Letters and Languages",
                nameAr = "كلية الآداب واللغات",
                descriptionEn = "Arabic, English and French language studies. Boudouaou Campus.",
                descriptionAr = "دراسات اللغة العربية والإنجليزية والفرنسية. قطب بودواو.",
                address = "Boudouaou Campus, Boudouaou, Boumerdes",
                phone = null,
                email = null,
                latitude = null,
                longitude = null
            )
        ).toInt()

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Arabic Language and Literature",
                nameAr = "اللغة والأدب العربي",
                descriptionEn = "Department of Arabic Language and Literature, Faculty of Letters and Languages.",
                descriptionAr = "قسم اللغة والأدب العربي، كلية الآداب واللغات.",
                specialties = "Bachelor: General Linguistics, Arabic Literature, Criticism and Methods\nMaster: General Linguistics, Ancient Arabic Literature, Modern and Contemporary Criticism",
                phone = null,
                specialtiesAr = "ليسانس: اللسانيات العامة، الأدب العربي، النقد والمناهج\nماستر: اللسانيات العامة، الأدب العربي القديم، النقد الحديث والمعاصر"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "English Language",
                nameAr = "اللغة الإنجليزية",
                descriptionEn = "Department of English Language, Faculty of Letters and Languages.",
                descriptionAr = "قسم اللغة الإنجليزية، كلية الآداب واللغات.",
                specialties = "Bachelor: English Language\nMaster: Literature and Civilizations, Language Sciences",
                phone = null,
                specialtiesAr = "ليسانس: اللغة الإنجليزية\nماستر: الأدب والحضارة، علوم اللغة"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "French Language",
                nameAr = "اللغة الفرنسية",
                descriptionEn = "Department of French Language, Faculty of Letters and Languages.",
                descriptionAr = "قسم اللغة الفرنسية، كلية الآداب واللغات.",
                specialties = "Bachelor: French Language\nMaster: Literature and Civilizations, Language Sciences, Didactics of Foreign Languages",
                phone = null,
                specialtiesAr = "ليسانس: اللغة الفرنسية\nماستر: الأدب والحضارة، علوم اللغة، تعليمية اللغات الأجنبية"
            )
        )
    }

    private fun addMedicine(helper: DatabaseHelper) {
        helper.insertFaculty(
            Faculty(
                nameEn = "Faculty of Medicine",
                nameAr = "كلية الطب",
                descriptionEn = "Medical training leading to the Doctor of Medicine degree.",
                descriptionAr = "تكوين طبي يؤدي إلى شهادة دكتور في الطب.",
                address = "Boumerdes",
                phone = "+21324799026",
                email = "annexemedecine@univ-boumerdes.dz",
                latitude = null,
                longitude = null
            )
        )
    }

    private fun addIgee(helper: DatabaseHelper) {
        val facultyId = helper.insertFaculty(
            Faculty(
                nameEn = "Institute of Electrical and Electronic Engineering (IGEE)",
                nameAr = "معهد الهندسة الكهربائية والإلكترونيك",
                descriptionEn = "Electrical and electronic engineering, taught in English. Center Campus.",
                descriptionAr = "الهندسة الكهربائية والإلكترونيك، والدراسة باللغة الإنجليزية. القطب المركزي.",
                address = "Boulevard de l'Independance, 35000 Boumerdes",
                phone = "+21324795763",
                email = null,
                latitude = 36.7570453,
                longitude = 3.4727315
            )
        ).toInt()

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Basic Education",
                nameAr = "التعليم الأساسي",
                descriptionEn = "Department of Basic Education, Institute of Electrical and Electronic Engineering (IGEE).",
                descriptionAr = "قسم التعليم الأساسي، معهد الهندسة الكهربائية والإلكترونيك.",
                specialties = null,
                phone = "+21324795758",
                specialtiesAr = null
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Electronics",
                nameAr = "الإلكترونيك",
                descriptionEn = "Department of Electronics, Institute of Electrical and Electronic Engineering (IGEE).",
                descriptionAr = "قسم الإلكترونيك، معهد الهندسة الكهربائية والإلكترونيك.",
                specialties = "Master: Computer Engineering, Integrated Electronics Design, Integrated Electronics Manufacturing",
                phone = "+21324795769",
                specialtiesAr = "ماستر: هندسة الحاسوب، تصميم الإلكترونيك المدمج، تصنيع الإلكترونيك المدمج"
            )
        )

        helper.insertDepartment(
            Department(
                facultyId = facultyId,
                nameEn = "Electrotechnics and Automatic Control",
                nameAr = "الإلكتروتقني والآلية",
                descriptionEn = "Department of Electrotechnics and Automatic Control, Institute of Electrical and Electronic Engineering (IGEE).",
                descriptionAr = "قسم الإلكتروتقني والآلية، معهد الهندسة الكهربائية والإلكترونيك.",
                specialties = "Master: Control Engineering, Power Engineering",
                phone = "+21324795768",
                specialtiesAr = "ماستر: هندسة التحكم، هندسة القدرة"
            )
        )
    }

    private fun addIsta(helper: DatabaseHelper) {
        helper.insertFaculty(
            Faculty(
                nameEn = "Institute of Applied Sciences and Techniques (ISTA)",
                nameAr = "معهد العلوم والتقنيات التطبيقية",
                descriptionEn = "Applied training in optics, precision mechanics, measurement, metrology and quality. North Campus.",
                descriptionAr = "تكوين تطبيقي في البصريات وميكانيك الدقة والقياسة والجودة. القطب الشمالي.",
                address = "North Campus, Avenue de l'Independance, 35000 Boumerdes",
                phone = "+21324795183",
                email = null,
                latitude = null,
                longitude = null
            )
        )
    }
}
