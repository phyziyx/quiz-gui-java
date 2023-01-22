-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.24-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

#   _____  _    ___     _______________     ____   __
#  |  __ \| |  | \ \   / /___  /_   _\ \   / /\ \ / /
#  | |__) | |__| |\ \_/ /   / /  | |  \ \_/ /  \ V /
#  |  ___/|  __  | \   /   / /   | |   \   /    > <
#  | |    | |  | |  | |   / /__ _| |_   | |    / . \
#  |_|    |_|  |_|  |_|  /_____|_____|  |_|   /_/ \_\

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for quiz
CREATE DATABASE IF NOT EXISTS `quiz` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `quiz`;

-- Dumping structure for table quiz.attempts
CREATE TABLE IF NOT EXISTS `attempts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quizId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `grade` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `quizId` (`quizId`) USING BTREE,
  KEY `userId` (`userId`) USING BTREE,
  CONSTRAINT `attempts_quizId_fk` FOREIGN KEY (`quizId`) REFERENCES `quiz` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `attempts_userId_fk` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table quiz.attempts: ~1 rows (approximately)
/*!40000 ALTER TABLE `attempts` DISABLE KEYS */;
INSERT INTO `attempts` (`id`, `quizId`, `userId`, `score`, `grade`) VALUES
	(1, 2, 3, 5, '66'),
	(2, 4, 9, 5, '50'),
	(4, 4, 3, 5, '100'),
	(5, 4, 3, 4, '80'),
	(6, 3, 3, 5, '100');
/*!40000 ALTER TABLE `attempts` ENABLE KEYS */;

-- Dumping structure for table quiz.questions
CREATE TABLE IF NOT EXISTS `questions` (
  `quizId` int(11) NOT NULL,
  `question` varchar(256) NOT NULL,
  `answer1` varchar(128) DEFAULT NULL,
  `answer2` varchar(128) DEFAULT NULL,
  `answer3` varchar(128) DEFAULT NULL,
  `answer4` varchar(128) DEFAULT NULL,
  `answer` int(11) DEFAULT NULL,
  KEY `quizId` (`quizId`),
  CONSTRAINT `quizId` FOREIGN KEY (`quizId`) REFERENCES `quiz` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table quiz.questions: ~18 rows (approximately)
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` (`quizId`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `answer`) VALUES
	(2, 'How many planets are there?', 'Eight', 'Seven', 'Six', 'Nine', 1),
	(2, 'What is the name of our Galaxy?', 'Andromeda Galaxy', 'Milky Way Galaxy', 'Cygnus A.', 'Virgo A', 2),
	(2, 'How many stars can you see at night time?', '100', '100,000,000', '100,000', 'Countless', 4),
	(2, 'The day on which the Sun\'s direct rays cross the celestial equator is called', 'the equinox', 'the aphelion', 'the solstice', 'the ecliptic', 1),
	(2, 'Who invented the telescope?', 'Hans Lippershey', 'Johannes Kepler', 'Hypatia', 'Galileo', 1),
	(2, 'Which of these objects is the farthest from the Sun?', '90377 Sedna', 'Saturn', 'Neptune', 'Kuiper belt', 1),
	(2, 'What term describes the alignment of three celestial bodies?', 'syzygy', 'symbology', 'suzerainty', 'sizzle', 1),
	(2, 'What is the smallest planet in the solar system by mass?', 'Mercury', 'Mars', 'Jupiter', 'Earth', 1),
	(3, 'First Country to sign Peace Treaty (1917)', 'England', 'Russia', 'USA', 'Germany', 2),
	(3, 'In which year USA joined Second World War', '1940', '1941', '1939', '1942', 2),
	(3, 'Axis powers in World War II?', 'Poland, Germany, Japan', 'Italy, Germany, Japan', 'France, Germany, Italy', 'Italy, Japan, Britain', 2),
	(3, 'On which side Japan fought First World War', 'Neutral', 'UK', 'Germany', 'Russia', 2),
	(3, 'First UK King during First World War?', 'John VII', 'George V', 'Anarew VIII', 'Philip I', 2),
	(4, 'Which game developer made Skyrim?', 'CD Projekt Red', 'Valve', 'Bethesda', 'Santa Monica Studios', 3),
	(4, 'Which is the most played Valve game?', 'Team Fortress 2', 'Call of Duty', 'Counter Strike GO', 'Battlefield', 3),
	(4, 'The Last of Us video game has sold this many copies:', '4M', '2M', '6M', '8M', 3),
	(4, 'Who made GTA V?', 'Electronic Arts', 'Naughty Dog', 'Rockstar', 'Activision', 3),
	(4, 'What is the main colour of Sonic?', 'Red', 'Purple', 'Blue', 'Yellow', 3);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;

-- Dumping structure for table quiz.quiz
CREATE TABLE IF NOT EXISTS `quiz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `owner` int(11) DEFAULT NULL,
  `scoreShow` tinyint(1) NOT NULL DEFAULT 1,
  `passingPercentage` tinyint(1) NOT NULL DEFAULT 0,
  `shuffleQuestions` tinyint(1) NOT NULL DEFAULT 0,
  `seconds` int(5) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `Index 1` (`id`),
  KEY `ownerFK` (`owner`),
  CONSTRAINT `ownerFK` FOREIGN KEY (`owner`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table quiz.quiz: ~3 rows (approximately)
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` (`id`, `name`, `owner`, `scoreShow`, `passingPercentage`, `shuffleQuestions`, `seconds`) VALUES
	(2, 'Space', 7, 1, 66, 1, 60),
	(3, 'World History', 1, 1, 0, 0, 120),
	(4, 'Gaming', 7, 0, 50, 0, 0);
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;

-- Dumping structure for table quiz.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(32) NOT NULL DEFAULT '',
  `pass` varchar(256) NOT NULL DEFAULT '',
  `power` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UNIQUE` (`user`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table quiz.users: ~3 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `user`, `pass`, `power`) VALUES
	(1, 'teacher', 'pass', 1),
	(2, 'teacher2', 'pass', 1),
	(3, 'student', 'pass', 0),
	(7, 'phyziyx', 'pass', 1),
	(8, 'student2', 'pass', 0),
	(9, 'student3', 'ali', 0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
