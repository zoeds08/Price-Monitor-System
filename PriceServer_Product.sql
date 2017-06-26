-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: PriceServer
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `product_id` varchar(45) NOT NULL,
  `category_name` varchar(45) NOT NULL,
  `title` longtext NOT NULL,
  `detail_url` longtext NOT NULL,
  `price` double NOT NULL,
  `old_price` double NOT NULL,
  `percentage` float NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES ('032119991X','Arts & Photography','MyMathLab: Student Access Kit','https://www.amazon.com/MyMathLab-Student-Hall-Pearson-Education/dp/032119991X',86.54,86.54,0.0114233),('0446310786','Arts & Photography','To Kill a Mockingbird','https://www.amazon.com/Kill-Mockingbird-Harper-Lee/dp/0446310786',6.79,6.79,0.12837),('0547152469','Arts & Photography','Peterson Field Guide to Birds of Eastern and Central North America, 6th Edition (Peterson Field Guides)','https://www.amazon.com/Peterson-Eastern-Central-America-Guides/dp/0547152469',11.22,11.22,0.0818331),('0671612972','Arts & Photography','Ben Hogan\'s Five Lessons: The Modern Fundamentals of Golf','https://www.amazon.com/Ben-Hogans-Five-Lessons-Fundamentals/dp/0671612972',8.53,8.53,0.104932),('0674729013','Arts & Photography','Make It Stick: The Science of Successful Learning','https://www.amazon.com/Make-Stick-Science-Successful-Learning/dp/0674729013',20.74,20.74,0.0459982),('076115678X','Arts & Photography','Curly Girl: The Handbook','https://www.amazon.com/Curly-Girl-Handbook-Michele-Bender/dp/076115678X',8.63,8.63,0.103842),('0765330342','Arts & Photography','A Dog\'s Purpose: A Novel for Humans','https://www.amazon.com/Dogs-Purpose-Novel-Humans/dp/0765330342',9.31,9.31,0.0969932),('080241270X','Arts & Photography','The 5 Love Languages: The Secret to Love that Lasts','https://www.amazon.com/Love-Languages-Secret-that-Lasts/dp/080241270X',9.59,9.59,0.0944287),('081091106X','Arts & Photography','Iggy Peck, Architect','https://www.amazon.com/Iggy-Peck-Architect-Andrea-Beaty/dp/081091106X',14.39,14.39,0.0649773),('0987650408','Arts & Photography','Treat Your Own Back','https://www.amazon.com/Treat-Your-Back-Robin-McKenzie/dp/0987650408',9.86,9.86,0.092081),('1476773084','Arts & Photography','Rediscovering Americanism: And the Tyranny of Progressivism','https://www.amazon.com/Rediscovering-Americanism-Progressivism-Mark-Levin/dp/1476773084',17.33,17.33,0.0545554),('1477819681','Arts & Photography','The Man of Legends','https://www.amazon.com/Man-Legends-Kenneth-Johnson/dp/1477819681',9.62,9.62,0.094162),('1477820183','Arts & Photography','Soho Dead (The Soho Series)','https://www.amazon.com/Soho-Dead-Greg-Keen/dp/1477820183',11.99,11.99,0.0769823),('1477848533','Arts & Photography','Lost in Arcadia: A Novel','https://www.amazon.com/Lost-Arcadia-Novel-Sean-Gandert/dp/1477848533',9.47,9.47,0.095511),('1477848665','Arts & Photography','Stillhouse Lake (Stillhouse Lake Series)','https://www.amazon.com/Stillhouse-Lake-Rachel-Caine/dp/1477848665',10.85,10.85,0.0843882),('B00006IA9F','Arts, Crafts & Sewing','Post-it Self-Stick Easel Pad, 25 x 30.5 Inches, 30-Sheet Pad (2 Pack)','https://www.amazon.com/Post-Self-Stick-Easel-Inches-30-Sheet/dp/B00006IA9F',27.07,27.07,0.0356252),('B00006IBNJ','Arts, Crafts & Sewing','Goo Gone Surface Safe Adhesive Remover, 8 oz','https://www.amazon.com/Goo-Gone-Surface-Adhesive-Remover/dp/B00006IBNJ',4.63,4.63,0.17762),('B00006IEEU','Arts, Crafts & Sewing','Prismacolor Premier Colored Pencils, Soft Core, 24-Count','https://www.amazon.com/Prismacolor-Premier-Colored-Pencils-24-Count/dp/B00006IEEU',11.47,11.47,0.0801925),('B0000YSH4W','Arts & Photography','The Giver','https://www.amazon.com/The-Giver/dp/B0000YSH4W',0,0,1),('B0006HUJJO','Arts, Crafts & Sewing','Elmer\'s Liquid School Glue, Washable, 1 Gallon, 1 Count','https://www.amazon.com/Elmers-Liquid-School-Washable-Gallon/dp/B0006HUJJO',13.95,13.95,0.0668896),('B0008G8G8Y','Arts, Crafts & Sewing','Sakura 30062 6-Piece Pigma Micron Ink Pen Set, Black','https://www.amazon.com/Sakura-30062-6-Piece-Pigma-Micron/dp/B0008G8G8Y',8.4,8.4,0.106383),('B000B7Q9FM','Arts, Crafts & Sewing','Singer 60-Inch Tape Measure','https://www.amazon.com/Singer-00218-60-Inch-Tape-Measure/dp/B000B7Q9FM',3.92,3.92,0.203252),('B000E23RSQ','Arts, Crafts & Sewing','Prismacolor Premier Colored Pencils, Soft Core, 72 Count','https://www.amazon.com/Prismacolor-Premier-Colored-Pencils-Count/dp/B000E23RSQ',29.99,29.99,0.0322685),('B000P0LNRE','Painting, Drawing & Art Supplies','Westcott Titanium Bonded Scissors, Straight-Handle, Pointed Tip, 8-Inch, Gray/Yellow, 2-pack (13901)','https://www.amazon.com/Westcott-Titanium-Scissors-Straight-Handle-13901/dp/B000P0LNRE',0,14.23,-0.86868),('B000TGSPV6','Arts, Crafts & Sewing','VELCRO Brand - Sticky Back - 3 1/2\" x 3/4\" Strips, 4 Sets - Black','https://www.amazon.com/VELCRO-Brand-Sticky-Strips-Black/dp/B000TGSPV6',2.98,2.98,0.251256),('B000ZTM72U','Painting, Drawing & Art Supplies','Rescue Tape Self-Fusing Silicone Tape, 1\" x 12\' x 20mil, Rectangular','https://www.amazon.com/Rescue-Tape-CECOMINOD019585-RT1000201206USCO/dp/B000ZTM72U',0,0,1),('B0013CDJTS','Arts, Crafts & Sewing','Pacon Lightweight Construction Paper, 9-Inches by 12-Inches, Assorted Colors, 500 Count (6555)','https://www.amazon.com/Pacon-Lightweight-Construction-9-Inches-12-Inches/dp/B0013CDJTS',8.69,8.99,0.0700701),('B001FKC584','Painting, Drawing & Art Supplies','SmartyKat Scratch Not Anti-Scratch Tape Scratch Deterrent Barrier','https://www.amazon.com/SmartyKat-Scratch-Anti-Scratch-Deterrent-Barrier/dp/B001FKC584',0,0,1),('B001OLRLF6','Arts & Photography','The Everything Kids\' Science Experiments Book: Boil Ice, Float Water, Measure Gravity-Challenge the World Around...','https://www.amazon.com/Everything-Kids-Science-Experiments-Gravity-Challenge-ebook/dp/B001OLRLF6',0,0,1),('B001QX2G9S','Painting, Drawing & Art Supplies','Rit Dye Liquid Fabric Dye, 8-Ounce, Black','https://www.amazon.com/Rit-Dye-Liquid-Fabric-8-Ounce/dp/B001QX2G9S',0,2.98,-0.497487),('B0021JD2S0','Arts, Crafts & Sewing','Grandma\'s Secret Spot Remover, 2-Ounce','https://www.amazon.com/Grandmas-Secret-Spot-Remover-2-Ounce/dp/B0021JD2S0',2.94,2.94,0.253807),('B0038DZZJC','Painting, Drawing & Art Supplies','Elmer\'s Glue-All Multi-Purpose Liquid Glue, Extra Strong, 1 Gallon, 1 Count','https://www.amazon.com/Elmers-Glue-All-Multi-Purpose-Liquid-Strong/dp/B0038DZZJC',13.4,13.4,0.0694444),('B003P2UOCO','Painting, Drawing & Art Supplies','Akro-Mils 10144 D 20-Inch by 16-Inch by 6-1/2-Inch Hardware and Craft Cabinet, Black','https://www.amazon.com/Akro-Mils-10144-20-Inch-16-Inch-Hardware/dp/B003P2UOCO',0,0,1),('B004C7MTLA','Arts, Crafts & Sewing','Glycerin Vegetable Kosher USP - 1 Quart (43 oz.)','https://www.amazon.com/Glycerin-Vegetable-Kosher-USP-Quart/dp/B004C7MTLA',12.97,12.97,0.071582),('B004F9QBGE','Arts, Crafts & Sewing','Prismacolor Scholar Pencil Sharpener','https://www.amazon.com/Prismacolor-1774266-Scholar-Pencil-Sharpener/dp/B004F9QBGE',1.5899999999999999,1.5899999999999999,0.3861),('B004FGMDOQ','Arts & Photography','Miss Peregrine\'s Home for Peculiar Children (Miss Peregrine\'s Peculiar Children Book 1)','https://www.amazon.com/Miss-Peregrines-Home-Peculiar-Children-ebook/dp/B004FGMDOQ',9.99,9.99,0.0909918),('B005KRSWM6','Arts, Crafts & Sewing','X-ACTO #1 Knife, Z Series With Safety Cap','https://www.amazon.com/X-Acto-XZ3601-X-ACTO-Knife-Safety/dp/B005KRSWM6',3.4,3.4,0.227273),('B005O2ZU68','Arts, Crafts & Sewing','Prismacolor Premier Colored Pencils, Soft Core, 150-Count','https://www.amazon.com/Prismacolor-Premier-Colored-Pencils-150-Count/dp/B005O2ZU68',58.54,58.54,0.0167954),('B005S2O61E','Arts, Crafts & Sewing','Silhouette Replacement Blade','https://www.amazon.com/Silhouette-SILH-BLADE-3-3T-Replacement-Blade/dp/B005S2O61E',8.93,8.93,0.100705),('B005VPVW3I','Arts, Crafts & Sewing','Silhouette Cameo Replacement Cutting Mat','https://www.amazon.com/Silhouette-Cameo-Replacement-Cutting-Mat/dp/B005VPVW3I',8.28,8.28,0.107759),('B007VBXB48','Painting, Drawing & Art Supplies','Scotch Thermal Laminating Pouches, 8.9 x 11.4-Inches, 3 mil thick, 100-Pack (TP3854-100)','https://www.amazon.com/Scotch-Laminating-11-4-Inches-100-Pack-TP3854-100/dp/B007VBXB48',0,11.23,-0.836468),('B008XDXU44','Arts, Crafts & Sewing','Elmer\'s All Purpose School Glue Sticks, Washable, 60 Pack, 0.24-ounce sticks','https://www.amazon.com/Elmers-Purpose-School-Washable-0-24-ounce/dp/B008XDXU44',0,11.18,-1),('B009IWNPXK','Painting, Drawing & Art Supplies','Infantino Squeeze Pouches, 4-Fl Oz,50 count','https://www.amazon.com/Infantino-Squeeze-Pouches-4-Fl-count/dp/B009IWNPXK',0,0,1),('B00AEBKYPG','Painting, Drawing & Art Supplies','Rescue Tape Self-Fusing Silicone Tape, 1\" x 12\' x 20mil, Rectangular','https://www.amazon.com/Rescue-Tape-RT1000201201USC-RT1000201201USCO/dp/B00AEBKYPG',0,0,1),('B00AFRWKRO','Arts, Crafts & Sewing','Beadaholique 500 Count Resealable Zipper Poly Bags, 2 by 3-Inch, 50mm by 100mm, Clear','https://www.amazon.com/Beadaholique-Count-Resealable-Zipper-3-Inch/dp/B00AFRWKRO',5.45,5.45,1),('B00ATJSD8I','Arts, Crafts & Sewing','Apple Barrel Acrylic Paint Set, 18 Piece (2-Ounce), PROMOABI Best Selling Colors I','https://www.amazon.com/Apple-Barrel-Acrylic-2-Ounce-PROMOABI/dp/B00ATJSD8I',18.07,18.07,0.0524384),('B00BSK750Y','Painting, Drawing & Art Supplies','Provo Craft Cricut Cutting Mats, Standard Grip, 12x12-Inch, 2-Pack','https://www.amazon.com/Cricut-Cutting-Standard-12x12-Inch-2-Pack/dp/B00BSK750Y',0,8.94,-0.798793),('B00BWU3HNY','Arts, Crafts & Sewing','AmazonBasics Thermal Laminating Pouches - 8.9-Inch x 11.4-Inch, Pack of 100','https://www.amazon.com/AmazonBasics-Thermal-Laminating-Pouches-11-4-Inch/dp/B00BWU3HNY',11.23,11.23,1),('B00CBAWIIY','Arts, Crafts & Sewing','Scotch Thermal Laminating Pouches, 8.9 x 11.4-Inches, 3 mil thick, 200-Pack (TP3854-200),Clear','https://www.amazon.com/Scotch-Laminating-11-4-Inches-200-Pack-TP3854-200/dp/B00CBAWIIY',15.78,15.78,0.0595948),('B00IMJ2L62','Arts, Crafts & Sewing','Avery No-Iron Clothing Labels, White, Assorted, Pack of 45 (40700)','https://www.amazon.com/Avery-No-Iron-Clothing-Assorted-40700/dp/B00IMJ2L62',9.24,9.24,0.0976562),('B00K1GTH02','Painting, Drawing & Art Supplies','Reynolds Wrap Extra Heavy Duty Foil Grill and Oven Bags (4 Count)','https://www.amazon.com/Reynolds-Extra-Heavy-Grill-Count/dp/B00K1GTH02',0,0,1),('B00LFCWSPM','Painting, Drawing & Art Supplies','Pentel Arts Oil Pastels, 50 Color Set (PHN-50)','https://www.amazon.com/Pentel-Arts-Pastels-Color-PHN-50/dp/B00LFCWSPM',0,0,1),('B00LOQMCDM','Arts, Crafts & Sewing','Command Picture & Frame Hanging Strips Value Pack, Large, White, 12-Pairs (17206-12ES)','https://www.amazon.com/Command-Picture-Hanging-12-Pairs-17206-12ES/dp/B00LOQMCDM',7.07,7.07,0.123916),('B00LW1APOC','Arts, Crafts & Sewing','Command Picture Hanging Strips Value Pack, Medium, White, 12-Pairs (17204-12ES)','https://www.amazon.com/Command-Picture-Hanging-12-Pairs-17204-12ES/dp/B00LW1APOC',6.78,6.78,0.128535),('B00NEW45XE','Arts & Photography','Ms. Marvel Vol. 1: No Normal (Ms. Marvel Series)','https://www.amazon.com/Ms-Marvel-Vol-No-Normal-ebook/dp/B00NEW45XE',0,0,1),('B00QK2RZSM','Painting, Drawing & Art Supplies','Tinksky 50 Sheets 3D Design Self-adhesive Tip Nail Art Stickers Decals(Random Color Pattern)','https://www.amazon.com/Tinksky-Sheets-Self-adhesive-Stickers-Pattern/dp/B00QK2RZSM',0,0,1),('B00V4EG6D6','Arts, Crafts & Sewing','Heartybay 10Pieces Round Pointed Tip Nylon Hair Brush Set, Blue','https://www.amazon.com/Heartybay-10Pieces-Round-Pointed-Nylon/dp/B00V4EG6D6',5.89,5.89,0.145138),('B010LHRFM2','Painting, Drawing & Art Supplies','Wacom Intuos Draw CTL490DW Digital Drawing and Graphics Tablet','https://www.amazon.com/Wacom-CTL490DW-Digital-Drawing-Graphics/dp/B010LHRFM2',0,75.99,-0.974023),('B01178RVI2','Arts, Crafts & Sewing','CCbetter Mini Hot Melt Glue Gun with 25pcs Glue Sticks High Temperature Melting Glue Gun Kit Flexible Trigger for DIY Small Craft Projects&Sealing and Quick Repairs(20-watt, Blue)','https://www.amazon.com/CCbetter-Temperature-Melting-Flexible-Projects/dp/B01178RVI2',10.59,10.59,0.0862813),('B017Y20RI6','Arts, Crafts & Sewing','Best Crafts 12\" X 10\' Feet Roll Transfer Paper w/ Grid- Perfect Alignment of Cameo or Cricut Self Adhesive Vinyl for Decals, Signs, Walls, Windows and Other Smooth Surfaces','https://www.amazon.com/Best-Transfer-Alignment-Adhesive-Surfaces/dp/B017Y20RI6',14.99,14.99,0.0625391),('B018HSB7GW','Painting, Drawing & Art Supplies','MarvelBeads Water Beads Rainbow Mix, 8 oz (20,000 beads) for Orbeez Spa Refill, Sensory Toys and Décor','https://www.amazon.com/MarvelBeads-Rainbow-Orbeez-Refill-Sensory/dp/B018HSB7GW',0,6.99,-0.749687),('B01D8IB10S','Arts, Crafts & Sewing','Sanford Colored pencil Charisma Colors 24-color set','https://www.amazon.com/Sanford-Colored-pencil-Charisma-24-color/dp/B01D8IB10S',81.82,81.82,0.0120744),('B01GLS0C2K','Arts, Crafts & Sewing','Shuttle Art 120 Unique Colors (No Duplicates) Gel Pens Gel Pen Set for Adult Coloring Books Art Markers','https://www.amazon.com/Shuttle-Art-Duplicates-Coloring-Markers/dp/B01GLS0C2K',18.98,18.98,0.0500501),('B01HZMNDFO','Arts, Crafts & Sewing','Colorations Washable Clear Glue, Gallon (Item # NGL)','https://www.amazon.com/Colorations-Washable-Clear-Gallon-NGL/dp/B01HZMNDFO',12.99,12.99,0.0714796),('B01IRU0URC','Arts & Photography','Medical Medium Life-Changing Foods: Save Yourself and the Ones You Love with the Hidden Healing Powers of Fruits...','https://www.amazon.com/Medical-Medium-Life-Changing-Foods-Vegetables-ebook/dp/B01IRU0URC',9.99,9.99,0.0909918),('B01M0OX3EW','Painting, Drawing & Art Supplies','GWHOLE Ring Size Adjuster with Silver Polishing Cloth,Set of 4 (2mm/3mm)','https://www.amazon.com/GWHOLE-Adjuster-Silver-Polishing-Cloth/dp/B01M0OX3EW',0,0,1),('B01M71S9DU','Arts, Crafts & Sewing','Tombow Fudenosuke Brush Pen 2 Pens Set','https://www.amazon.com/Tombow-Fudenosuke-Brush-Pen-Pens/dp/B01M71S9DU',4.9399999999999995,4.9399999999999995,0),('B06WVD2ZF4','Arts, Crafts & Sewing','Soft Clay (White)','https://www.amazon.com/Daiso-Japan-Soft-Clay-White/dp/B06WVD2ZF4',3.41,3.41,0.226757),('B07352GK4X','Arts & Photography','Red Havoc Bad Bear (Red Havoc Panthers Book 5)','https://www.amazon.com/Red-Havoc-Bear-Panthers-Book-ebook/dp/B07352GK4X',0,0,1),('B0735B31WT','Arts & Photography','She Fell In Love With A Street King 2','https://www.amazon.com/She-Fell-Love-Street-King-ebook/dp/B0735B31WT',0,0,1);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-26 10:12:09
