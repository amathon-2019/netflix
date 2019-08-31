from django.shortcuts import render, redirect
from django.contrib import auth
from .models import User, Account, Accountuserrelationship

def home(request):
    if request.user.is_authenticated:
        return redirect('adminPage')
    else:
        return render(request, 'home.html')

def loginProc(request):
    if request.method == "POST":
        username = request.POST['username']
        password = request.POST['password']
        user = auth.authenticate(request, username=username, password=password)
        if user is not None:
            auth.login(request, user)
            return redirect('mainpage')
        else:
            return render(request, 'home.html', {'error': 'Username or Password is incorrect'})
    return render(request, 'home.html')

def adminPage(request):
    userCnt = User.objects.count()
    party = Account.objects.filter()
    partyCnt = party.count()
    avgMember = round(userCnt/partyCnt, 4)
    relationCnt = Accountuserrelationship.objects.count()

    return render(request, 'index.html', {'userCnt':userCnt, 'partyCnt':partyCnt, 'avgMember':avgMember,
                                          'relationCnt':relationCnt, 'object_list':party})

def partyView(request, id):
    relation = Accountuserrelationship.objects.filter(account_id=id)

    return render(request, 'relationView.html', {'relation': relation})