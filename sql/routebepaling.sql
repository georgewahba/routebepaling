-- -------------------------------------------------------------
-- TablePlus 5.3.6(495)
--
-- https://tableplus.com/
--
-- Database: routebepaling
-- Generation Time: 2023-04-26 23:50:27.9860
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `adressen`;
CREATE TABLE `adressen` (
  `id` int(11) NOT NULL,
  `stad` varchar(50) DEFAULT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  `straatnaam` varchar(100) DEFAULT NULL,
  `huisnummer` varchar(10) DEFAULT NULL,
  `route_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `route`;
CREATE TABLE `route` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

INSERT INTO `adressen` (`id`, `stad`, `postcode`, `straatnaam`, `huisnummer`, `route_id`) VALUES
(1, 'Amsterdam', '1012AB', 'Damstraat', '1', 1),
(2, 'Rotterdam', '3011EA', 'Coolsingel', '42', 1),
(3, 'Utrecht', '3511ER', 'Oudegracht', '123', 1),
(4, 'Den Haag', '2511CC', 'Binnenhof', '1', 1),
(5, 'Groningen', '9712AA', 'Grote Markt', '1', 1),
(6, 'Eindhoven', '5611AE', 'Markt', '21', 1),
(7, 'Maastricht', '6211CH', 'Vrijthof', '1', 1),
(8, 'Haarlem', '2011PL', 'Grote Houtstraat', '1', 1),
(9, 'Leeuwarden', '8911DM', 'Nieuwestad', '1', 1),
(10, 'Arnhem', '6811BJ', 'Korenmarkt', '1', 1),
(11, 'Amsterdam', '1012AB', 'Damstraat', '1', 2),
(12, 'Rotterdam', '3011EA', 'Coolsingel', '42', 2),
(13, 'Utrecht', '3511ER', 'Oudegracht', '123', 2);

INSERT INTO `route` (`id`, `user_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'george', '12345'),
(2, 'test2', '12345');



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;