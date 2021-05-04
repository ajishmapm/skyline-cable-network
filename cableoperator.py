from flask import Flask,render_template,request,redirect
from DBConnection import Db
app = Flask(__name__)


@app.route('/')
def login():
    return render_template("login_new.html")


@app.route('/login_post',methods=['post'])
def login_post():
    username=request.form['username']
    password=request.form['password']
    db=Db()
    qry=db.selectOne("select * from login where user_name='"+username+"' and password='"+password+"'")
    if qry is not None:
        if qry['user_type']=="serviceagency":
            return redirect("/service_header")
        else:
            return '<script>alert("logging in...");window.location="/home"</script>'

    return render_template("login.html")

@app.route('/service_header')
def service_header():
    return render_template("service_agency/service_header.html")

@app.route('/add_cash_collector')
def add_cash_collector():
    return render_template("service_agency/add_cash_collector.html")

@app.route('/add_cash_collector1',methods=['post'])
def add_cash_collector1():
    name=request.form['name']
    number=request.form['name2']
    email=request.form['name3']
    place=request.form['name4']
    area=request.form['name5']
    db=Db()
    query=db.insert("insert into cash_collector VALUES ('','"+name+"','"+number+"','"+email+"','"+place+"','"+area+"')")

    return '<script>alert("success");window.location="/add_cash_collector"</script>'






@app.route('/add_channel')
def add_channel():
    return render_template("service_agency/add_channel.html")



@app.route('/channel_add_post',methods=['post'])
def channel_add_post():
    channel=request.form['ch_name']
    channel_type=request.form['t']
    db=Db()
    query = db.insert("insert into channel VALUES ('','" + channel + "','" + channel_type + "')")
    return '<script>alert("success");window.location="/add_channel"</script>'

@app.route('/view_channel')
def view_channel():
    db=Db()
    qry=db.select("select * from channel")
    return render_template("service_agency/view_channel.html",data=qry)

@app.route('/delete_channel/<i>')
def delete_channel(i):
    db = Db()
    qry=db.delete("delete from channel where channel_id='"+str(i)+"'")
    return view_channel()



@app.route('/add_channel_package')
def add_channel_package():
    db=Db()
    qry="select * from channel "
    res=db.select(qry)
    print(res)
    return render_template("service_agency/add_channel_package.html",data=res)



@app.route('/channel_add_package',methods=['post'])
def channel_add_package():
    ch_package_name=request.form['ch_package']
    Rate=request.form['Rate']
    validity=request.form['Validity']
    channel=request.form.getlist('ch')
    db=Db()
    ch_pkg_id = db.insert("insert into channel_package VALUES ('','" + ch_package_name + "','"+Rate+"','"+ validity + "')")
    for chn in channel:
        db.insert("insert into channels VALUES (null,'"+chn+"','"+str(ch_pkg_id)+"')")
    return '<script>alert("success");window.location="/add_channel"</script>'









@app.route('/add_internet_package')
def add_internet_package():
    return render_template("service_agency/add_internet_package.html")



@app.route('/internet_add_package',methods=['post'])
def internet_add_package():
    package_name=request.form['i_package_name']
    rate=request.form['i_rate']
    validity=request.form['i_validity']
    speed=request.form['Speed']
    db=Db()
    query = db.insert("insert into internet_package VALUES ('','" + speed + "','" + rate + "','" + validity + "','" + package_name + "')")
    return '<script>alert("success");window.location="/add_internet_package"</script>'



@app.route('/view_internet_package')
def view_internet_package():
    db=Db()
    qry=db.select("select * from internet_package")
    return render_template("service_agency/view_internet_package.html",data=qry)



@app.route('/add_notification')
def add_notification():
    return render_template("service_agency/add_notification.html")

@app.route('/send_notification',methods=['post'])
def send_notification():
    noti=request.form['n']
    db=Db()
    query=db.insert("insert into notification VALUES ('','"+noti+"',curdate())")
    return '<script>alert("success");window.location="/add_notification"</script>'


@app.route('/view_notification')
def view_notification():
    db = Db()
    qry=db.select("select * from notification")
    return render_template("service_agency/view_notification.html",data=qry)



@app.route('/delete_notification/<i>')
def delete_notification(i):
    db = Db()
    qry=db.delete("delete from notification where notification_id='"+str(i)+"'")
    return view_notification()

@app.route('/add_maintenance')
def add_maintenance():
    return render_template("service_agency/add_maintenance.html")

@app.route('/add_maintenance1',methods=['post'])
def add_maintenance1():

    name=request.form['name']
    number=request.form['name2']
    email=request.form['name3']
    place=request.form['name4']
    area=request.form['name5']
    db=Db()
    query=db.insert("insert into maintenance VALUES ('','"+name+"','"+number+"','"+email+"','"+place+"','"+area+"')")

    return '<script>alert("success");window.location="/add_maintenance"</script>'


@app.route('/view_maintenance')
def view_maintenance():
    db = Db()
    qry=db.select("select * from maintenance")
    return render_template("service_agency/view_maintenance.html",data=qry)


@app.route('/delete_maintenance/<i>')
def delete_maintenance(i):
    db = Db()
    qry=db.delete("delete from maintenance where maintenance_id='"+str(i)+"'")
    return view_maintenance()


@app.route('/edit_maintenance/<i>')
def edit_maintenance(i):
    db=Db()
    qry=db.selectOne("select * from maintenance where  maintenance_id='"+str(i)+"' ")
    return render_template("service_agency/edit_maintenance.html",data=qry)


@app.route('/edit_maintanance_post/<i>',methods=['post'])
def edit_maintanance_post(i):

    name=request.form['name']
    number=request.form['name2']
    email=request.form['name3']
    place=request.form['name4']
    area=request.form['name5']
    db=Db()
    query=db.update("update maintenance set maintenance_name='"+name+"',ph_number='"+number+"',email='"+email+"',place='"+place+"',area='"+area+"' where maintenance_id='"+str(i)+"'")
    return '<script>alert("Updated Successfully");window.location="/view_maintenance"</script>'

@app.route('/allocate_work')
def allocate_work():
    return render_template("service_agency/allocate_work.html")

@app.route('/channel_management')
def channel_management():
    return render_template("service_agency/channel_management.html")

@app.route('/view_cash_collector')
def view_cash_collector():
    db = Db()
    qry=db.select("select * from cash_collector")
    return render_template("service_agency/view_cash_collector.html",data=qry)


@app.route('/delete_cash_collector/<i>')
def delete_cash_collector(i):
    db = Db()
    qry=db.delete("delete from cash_collector where cash_collector_id='"+str(i)+"'")
    return view_maintenance()




@app.route('/edit_cash_collector/<i>')
def edit_cash_collector(i):
    db = Db()
    qry=db.selectOne("select * from cash_collector where cash_collector_id='"+str(i)+"'")
    return render_template('service_agency/edit_cash_collector.html',data=qry)






@app.route('/workallocate_cashcollector/<i>')
def workallocate_cashcollector(i):
    return render_template('service_agency/workallocate_cashcollector.html', data=i)

@app.route('/workallocate_cashcollector2/<i>',methods=['post'])
def workallocate_cashcollector2(i):

    area=request.form['t']


    db = Db()
    qry = db.insert( "insert into cashcollector_work VALUES ('','" + area + "','"+i+"',curdate())")
    return '<script>alert("Updated Successfully");window.location="/view_work_allocated"</script>'




@app.route('/view_work_allocated')
def view_work_allocated():
    db=Db()
    qry=db.select("select * from cashcollector_work,cash_collector where cash_collector.cash_collector_id=cashcollector_work.cash_collector_id")
    return render_template("service_agency/view_work_allocated.html",data=qry)










@app.route('/view_channel_packages')
def view_channel_packages():
    db=Db()
    qry=db.select("select * from channel_package")
    return render_template("service_agency/view_channel_packages.html",data=qry)





@app.route('/channels_in_package/<i>')
def channels_in_package(i):
    db=Db()
    qry=db.select("select * from channels,channel where  ch_package_id='"+str(i)+"' and channels.channel_id=channel.channel_id ")
    return render_template("service_agency/channels_in_package.html",data=qry)





@app.route('/delete_channel_package/<i>')
def delete_channel_package(i):
    db = Db()
    qry=db.delete("delete from channel_package where ch_package_id='"+str(i)+"'")
    return view_channel()




@app.route('/delete_internet_package/<i>')
def delete_internet_package(i):
    db = Db()
    qry=db.delete("delete from internet_package where int_package_id='"+str(i)+"'")
    return view_internet_package()











@app.route('/view_user_request')
def view_user_request():
    return render_template("service_agency/view_user_request.html")

@app.route('/view_user_request_internet')
def view_user_request_internet():
    return render_template("service_agency/view_user_request_internet.html")

#
# @app.route('/view_user_request_internet')
# def view_user_request_internet():
#     return render_template("service_agency/view_work_allocated.html")
#



if __name__ == '__main__':
    app.run()
