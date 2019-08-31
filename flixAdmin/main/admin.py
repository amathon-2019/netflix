from django.contrib import admin
from .models import User, Account, Accountuserrelationship

admin.site.register(User)
admin.site.register(Account)
admin.site.register(Accountuserrelationship)