import requests
from bs4 import BeautifulSoup
from openpyxl import load_workbook

""" BAĞLANTI FONKSİYONU """
def Connection(firstUrl, secondUrl, pageNumber):
    response = requests.get(firstUrl+str(pageNumber)+secondUrl)
    return response

""" EXCEL YÜKLEME """
def LoadFile(fileName):
    excel = load_workbook(fileName)
    return excel

""" EXCEL WORKSHEET AÇMA FONKSİYONU"""
def PrepareFile(excel):
    worksheet = excel.active
    return worksheet

""" EXCEL VERİ EKLEME FONKSİYONU """
def AppendToFile(worksheet, name, price, stockCode, kdv):
    worksheet.append(["" + stockCode, "" + name, "" +kdv, "" + price, "" + ("https://images.bayipro.com/b2b.enderyapi.com.tr/Images/Product/ProductPageThumbnail/"+""+stockCode+"_1.jpg")])

""" EXCEL DOSYAYI KAYDETME VE KAPATMA FONKSİYONU """
def SaveAndCloseFile(excel, fileName):
    excel.save(fileName)
    excel.close()

""" SAYFA İÇERİĞİNİ TEXT'E ÇEVİRME FONKSİYONU """
def PageContent(response):
    pageContent = BeautifulSoup(response.text, 'html.parser')
    return pageContent

""" ARAMA FONKSİYONU """
def Find(pageContent, supTag, subTag, subTagContent):
    tagContent = pageContent.find_all(supTag, attrs={subTag, subTagContent})
    return tagContent

""" TEK BİR ÜRÜNÜN BİLGİLERİNİ İSİME GÖRE BULAN FONKSİYON """
def SearchByName(productInformation, iter, nameContent):
    information = productInformation[iter].find("input", {"name": nameContent}).get("value")
    return information

def MainFunction():

    """ URL ADRESLERİ """
    firstUrl = "https://www.enderyapi.com.tr/emagaza/78/"
    secondUrl = "/elektrikli-el-aletleri/"
    fileName = "Elektrikli.xlsx"

    excel = LoadFile(fileName)
    worksheet = PrepareFile(excel)

    """ ANA DÖNGÜ """
    for pageNumber in range (1,9):

        """ SAYFA BAĞLANTISI """
        response = Connection(firstUrl, secondUrl, pageNumber)
        pageContent = PageContent(response)
        productNames = Find(pageContent, "div", "class", "fiyat secilemez")

        if(pageNumber == 8):
            for iter in range(0,11):
                name = SearchByName(productNames, iter, nameContent="adi")
                price = SearchByName(productNames, iter, nameContent="toplamfiyat")
                stockCode = SearchByName(productNames, iter, nameContent="stokkodu")
                kdv = SearchByName(productNames, iter, nameContent="kdv")

                AppendToFile(worksheet, name, price, stockCode, kdv)

        else:
            for iter in range(0,24):
                name = SearchByName(productNames, iter, "adi")
                price = SearchByName(productNames, iter, "toplamfiyat")
                stockCode = SearchByName(productNames, iter, "stokkodu")
                kdv = SearchByName(productNames, iter, "kdv")

                AppendToFile(worksheet, name, price, stockCode, kdv)

    SaveAndCloseFile(excel, fileName)

MainFunction()