
from flask import Flask,render_template,request,redirect
from DBConnection import Db
import demjson
app = Flask(__name__)

@app.route('/login_post',methods=['post'])
def login_post():
    username=request.form['username']
    password=request.form['password']
    db=Db()
    qry=db.selectOne("select * from login where user_name='"+username+"' and password='"+password+"'")
    res1 = {}
    if qry is not None:
        id=qry['login_id']
        type=qry['user_type']
        if type=='user':
            res1['type1']=type
            res1['lid']=id
            res1['status']='ok'
            return demjson.encode(res1)
        else:
            res1['status']='none'
            return demjson.encode(res1)
    else:
        res1['status'] = 'none'
        return demjson.encode(res1)

@app.route('/user_registration1',methods=['post'])
def user_registration1():
    db = Db()
    name=request.form['name']
    address=request.form['address']
    email=request.form['email']
    phone_no=request.form['phone_no']
    password = request.form['password']
    confirm = request.form['cpassword']
    res1={}
    if password==confirm:
        qry=db.insert("insert into login values('','"+email+"','"+password+"','user')")
        query=db.insert("insert into registration VALUES ('"+str(qry)+"','"+address+"','"+email+"','"+name+"','"+phone_no+"')")
        res1['status']='ok'
        return demjson.encode(res1)
    else:
        res1['status'] = 'none'
        return demjson.encode(res1)



@app.route('/channel_package',methods=['post'])
def channel_package():
    db = Db()
    # channel_package = request.form['ch_package']
    # rate = request.form['rate']
    # validity = request.form['validity']
    qry = db.select("select * from channel_package")
    res1 = {}
    if qry is not None:
        res1['status']='ok'
        res1['data']=qry
        return demjson.encode(res1)
    else:
        res1['status']='none'
        return demjson.encode(res1)


if __name__ == '__main__':
    app.run(host='0.0.0.0')
