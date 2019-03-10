-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Mar 05, 2019 at 12:09 AM
-- Server version: 5.7.23
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `store`
--

-- --------------------------------------------------------

--
-- Table structure for table `acct_seq`
--

CREATE TABLE `acct_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `address_line1` varchar(255) NOT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `creation_date_time` datetime DEFAULT NULL,
  `state` varchar(255) NOT NULL,
  `updated_date_time` datetime DEFAULT NULL,
  `zipcode` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Table structure for table `addr_seq`
--

CREATE TABLE `addr_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `brand_seq`
--

CREATE TABLE `brand_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cat_seq`
--

CREATE TABLE `cat_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Table structure for table `files`
--

CREATE TABLE `files` (
  `id` int(11) NOT NULL,
  `data` longblob,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `image_seq`
--

CREATE TABLE `image_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `quantity` int(11) NOT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `is_published` bit(1) NOT NULL,
  `aisle` int(11) NOT NULL,
  `expiration_date` varchar(255) NOT NULL,
  `store_info_id` int(11) DEFAULT NULL,
  `image_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- Table structure for table `item_seq`
--

CREATE TABLE `item_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `owner_account_info`
--

CREATE TABLE `owner_account_info` (
  `id` int(11) NOT NULL,
  `creation_date_time` datetime DEFAULT NULL,
  `date_of_birth` varchar(255) NOT NULL,
  `driving_lisence` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `updated_date_time` datetime DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `store_info`
--

CREATE TABLE `store_info` (
  `id` int(11) NOT NULL,
  `creation_date_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `store_license` varchar(255) NOT NULL,
  `updated_date_time` datetime DEFAULT NULL,
  `owner_account_info_id` int(11) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `store_seq`
--

CREATE TABLE `store_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `files`
--
ALTER TABLE `files`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhie4w6g67io9k67mf87clka9l` (`brand_id`),
  ADD KEY `FK2n9w8d0dp4bsfra9dcg0046l4` (`category_id`),
  ADD KEY `FKfji0lqw5ncfdysqst8qolqxrk` (`image_id`),
  ADD KEY `FKnhvexf0q7ahy8kglnidhy32l2` (`store_info_id`);

--
-- Indexes for table `owner_account_info`
--
ALTER TABLE `owner_account_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhhlp06a8bv4tvtfc2xj2u4pr0` (`address_id`);

--
-- Indexes for table `store_info`
--
ALTER TABLE `store_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjhftxucoh7dv0dkkwpai0hfx6` (`owner_account_info_id`),
  ADD KEY `FKcj5tdaq0nlhvghtr4tfb8bs02` (`address_id`);
