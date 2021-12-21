-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Дек 20 2021 г., 17:57
-- Версия сервера: 10.4.18-MariaDB
-- Версия PHP: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `apothekedb`
--

-- --------------------------------------------------------

--
-- Структура таблицы `accounttype`
--

CREATE TABLE `accounttype` (
  `ID` int(45) NOT NULL,
  `accType` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `accounttype`
--

INSERT INTO `accounttype` (`ID`, `accType`) VALUES
(1, 'Pharmacist'),
(2, 'Deliveryman');

-- --------------------------------------------------------

--
-- Структура таблицы `authorization`
--

CREATE TABLE `authorization` (
  `ID` int(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ID_accType` int(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `authorization`
--

INSERT INTO `authorization` (`ID`, `login`, `password`, `ID_accType`) VALUES
(1, 'pharm', 'pharm123', 1),
(2, 'deliv', 'deliv123', 2),
(3, 'pharm2', 'pharm2123', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `delivered`
--

CREATE TABLE `delivered` (
  `ID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `delivered_date` date NOT NULL,
  `ID_drug` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `delivered`
--

INSERT INTO `delivered` (`ID`, `name`, `quantity`, `delivered_date`, `ID_drug`) VALUES
(1, 'sitramon', 1, '2021-11-04', 1),
(2, 'sitramon', 1, '2021-12-10', 1),
(3, 'sitramon', 1, '2021-12-10', 1),
(4, 'sitramon', 1, '2021-12-10', 1),
(5, 'sitramon', 7, '2021-12-10', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `deliveryman`
--

CREATE TABLE `deliveryman` (
  `ID` int(45) NOT NULL,
  `Vorname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `deliveryman`
--

INSERT INTO `deliveryman` (`ID`, `Vorname`, `name`, `age`) VALUES
(1, 'Adamus', 'Bill', 34);

-- --------------------------------------------------------

--
-- Структура таблицы `dialogwindows`
--

CREATE TABLE `dialogwindows` (
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `dialogwindows`
--

INSERT INTO `dialogwindows` (`name`, `value`) VALUES
('dialog window 1', 'sitramon');

-- --------------------------------------------------------

--
-- Структура таблицы `drugs`
--

CREATE TABLE `drugs` (
  `ID` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  `manufacturer` varchar(255) NOT NULL,
  `symptomatology` varchar(255) NOT NULL,
  `price` int(255) NOT NULL,
  `discount` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `drugs`
--

INSERT INTO `drugs` (`ID`, `name`, `quantity`, `manufacturer`, `symptomatology`, `price`, `discount`) VALUES
(1, 'sitramon', 81, 'HDS', 'headache', 10, 0);

-- --------------------------------------------------------

--
-- Структура таблицы `for_deliveryman`
--

CREATE TABLE `for_deliveryman` (
  `ID` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(255) UNSIGNED NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `new_drug_for_delivery`
--

CREATE TABLE `new_drug_for_delivery` (
  `name` varchar(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  `manufacturer` varchar(255) NOT NULL,
  `symptomatology` varchar(255) NOT NULL,
  `price` int(255) NOT NULL,
  `discount` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `pharmacist`
--

CREATE TABLE `pharmacist` (
  `ID` int(45) NOT NULL,
  `Vorname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `age` int(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `pharmacist`
--

INSERT INTO `pharmacist` (`ID`, `Vorname`, `name`, `age`) VALUES
(1, 'Tomson', 'Jake', 23);

-- --------------------------------------------------------

--
-- Структура таблицы `sold`
--

CREATE TABLE `sold` (
  `ID` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  `price` int(255) NOT NULL,
  `date` date NOT NULL,
  `discount` int(255) NOT NULL,
  `ID_drug` int(255) NOT NULL,
  `total` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `sold`
--

INSERT INTO `sold` (`ID`, `name`, `quantity`, `price`, `date`, `discount`, `ID_drug`, `total`) VALUES
(1, 'jack', 23, 0, '0000-00-00', 20, 1, 393),
(2, 'JACK1', 230, 0, '2020-08-19', 30, 1, 345),
(3, 'sitramon', 10, 100, '2021-11-03', 50, 1, 1000),
(4, 'sitramon', 10, 100, '2021-11-03', 50, 1, 1000),
(5, 'sitramon', 10, 100, '2021-11-04', 50, 1, 1000),
(6, 'sitramon', 10, 100, '2021-11-04', 50, 1, 500),
(7, 'sitramon', 10, 100, '2021-11-04', 0, 1, 1000),
(8, 'sitramon', 10, 10, '2021-12-10', 50, 1, 50);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `accounttype`
--
ALTER TABLE `accounttype`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID` (`ID`);

--
-- Индексы таблицы `authorization`
--
ALTER TABLE `authorization`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_accType` (`ID_accType`);

--
-- Индексы таблицы `delivered`
--
ALTER TABLE `delivered`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_drug` (`ID_drug`);

--
-- Индексы таблицы `deliveryman`
--
ALTER TABLE `deliveryman`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `drugs`
--
ALTER TABLE `drugs`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID` (`ID`);

--
-- Индексы таблицы `for_deliveryman`
--
ALTER TABLE `for_deliveryman`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `pharmacist`
--
ALTER TABLE `pharmacist`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `sold`
--
ALTER TABLE `sold`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_drug` (`ID_drug`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `authorization`
--
ALTER TABLE `authorization`
  MODIFY `ID` int(45) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `delivered`
--
ALTER TABLE `delivered`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `drugs`
--
ALTER TABLE `drugs`
  MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `for_deliveryman`
--
ALTER TABLE `for_deliveryman`
  MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `sold`
--
ALTER TABLE `sold`
  MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `authorization`
--
ALTER TABLE `authorization`
  ADD CONSTRAINT `authorization_ibfk_1` FOREIGN KEY (`ID_accType`) REFERENCES `accounttype` (`ID`) ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `delivered`
--
ALTER TABLE `delivered`
  ADD CONSTRAINT `delivered_ibfk_1` FOREIGN KEY (`ID_drug`) REFERENCES `drugs` (`ID`) ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `sold`
--
ALTER TABLE `sold`
  ADD CONSTRAINT `sold_ibfk_1` FOREIGN KEY (`ID_drug`) REFERENCES `drugs` (`ID`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
