from django.contrib import admin
from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name='mainpage'),
    path('loginProc', views.loginProc, name='loginProc'),
    path('adminPage', views.adminPage, name='adminPage'),
    path('partyView/<int:id>', views.partyView, name='partyView')
]