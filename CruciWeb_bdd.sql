-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : sam. 04 jan. 2025 à 20:47
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `CruciWeb`
--

-- --------------------------------------------------------

--
-- Structure de la table `FinishedGrids`
--

CREATE TABLE `FinishedGrids` (
  `idFGrid` int(11) NOT NULL,
  `dim_x` int(11) NOT NULL,
  `dim_y` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `difficulty` enum('easy','medium','hard','') NOT NULL,
  `created_at` date NOT NULL,
  `solutions_rows` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`solutions_rows`)),
  `solutions_columns` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`solutions_columns`)),
  `hints_rows` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`hints_rows`)),
  `hints_columns` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`hints_columns`)),
  `idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `SavedGrids`
--

CREATE TABLE `SavedGrids` (
  `idSGrid` int(11) NOT NULL,
  `answers_rows` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`answers_rows`)),
  `answers_columns` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`answers_columns`)),
  `idUser` int(11) NOT NULL,
  `idFGrid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Users`
--

CREATE TABLE `Users` (
  `idUser` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Users`
--

INSERT INTO `Users` (`idUser`, `username`, `password`, `role`, `email`) VALUES
(2, 'admin', 'admin', 'admin', 'admin@admin.fr');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `FinishedGrids`
--
ALTER TABLE `FinishedGrids`
  ADD PRIMARY KEY (`idFGrid`),
  ADD KEY `FK_FinishedGrid_idUser_Users` (`idUser`);

--
-- Index pour la table `SavedGrids`
--
ALTER TABLE `SavedGrids`
  ADD PRIMARY KEY (`idSGrid`),
  ADD KEY `FK_SavedGrids_idUser_Users` (`idUser`),
  ADD KEY `FK_SavedGrids_idFGrid_FinishedGrids` (`idFGrid`);

--
-- Index pour la table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `UnUsersUsername` (`username`),
  ADD UNIQUE KEY `UnUsersEmail` (`email`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `FinishedGrids`
--
ALTER TABLE `FinishedGrids`
  ADD CONSTRAINT `FK_FinishedGrid_idUser_Users` FOREIGN KEY (`idUser`) REFERENCES `Users` (`idUser`) ON DELETE CASCADE;

--
-- Contraintes pour la table `SavedGrids`
--
ALTER TABLE `SavedGrids`
  ADD CONSTRAINT `FK_SavedGrids_idFGrid_FinishedGrids` FOREIGN KEY (`idFGrid`) REFERENCES `FinishedGrids` (`idFGrid`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_SavedGrids_idUser_Users` FOREIGN KEY (`idUser`) REFERENCES `Users` (`idUser`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
