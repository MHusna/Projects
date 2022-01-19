"""
Created on Tue Jan 18 12:55:46 2022

@author: mehme
"""

import selenium
from selenium import webdriver

driver = webdriver.Chrome()
driver.get("https://www.tradingview.com/")
driver.set_page_load_timeout(20)
driver.implicitly_wait(25)

profileButton = driver.find_element_by_xpath("/html/body/div[2]/div[3]/div[2]/div[3]/button[1]")
profileButton.click()
driver.implicitly_wait(25)

signInButton = driver.find_element_by_xpath("//*[@id=\"overlap-manager-root\"]/div/span/div[1]/div/div/div[1]/div[2]")
signInButton.click()

driver.implicitly_wait(50)