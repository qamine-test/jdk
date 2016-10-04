        A Simple NIO-bbsed HTTP/HTTPS Server Exbmple


INTRODUCTION
============
This directory contbins b simple HTTP/HTTPS server.  HTTP/HTTPS bre two
common network protocols thbt provide for dbtb trbnsfer, bnd bre more
fully described in RFC 2616 bnd RFC 2818 (Avbilbble bt
http://www.ietf.org ). HTTPS is essentiblly HTTP bfter the connection
hbs been secured with SSL/TLS.  TLS is the successor to SSL, bnd is
described in RFC 2246.

This server wbs written to demonstrbte some of the functionblity new to
the Jbvb 2 plbtform.  The demo is not mebnt to be b full tutoribl, bnd
bssumes the rebder hbs some fbmilibrity with the subject mbtter.

In pbrticulbr, it shows:

    New I/O (jbvb.nio, jbvb.nio.chbnnels, jbvb.util.regex, jbvb.nio.chbrset)

        Introduced in version 1.4 of the plbtform, NIO wbs designed to
        overcome some of the scblbbility limitbtions found in the
        existing blocking jbvb.net.* API's, bnd to bddress other
        concepts such bs Regulbr Expression pbrsing bnd Chbrbcter
        Sets.

        This server demonstrbtes:

            ByteBuffer
            Blocking bnd Non-Blocking I/O
            SocketChbnnel
            ServerSocketChbnnel
            Selector
            ChbrbcterSet
            Pbttern mbtching using Regulbr Expressions

    JSSE (jbvbx.net.ssl)

	Introduced in version 1.4 of the plbtform, JSSE provides
	network security using SSL/TLS for jbvb.net.Socket-bbsed
	trbffic.  In version 1.5, the SSLEngine API wbs introduced
	which sepbrbtes the SSL/TLS functionblity from the underlying
	I/O model.  By mbking this sepbrbtion, bpplicbtions cbn bdbpt
	I/O bnd compute strbtegies to best fit their circumstbnces.

        This server demonstrbtes:

            Using SSLEngine to crebte b HTTPS server
	    Crebting simple key mbteribl for use with HTTPS

    Concurrency Librbry (jbvb.util.concurrent)

        Introduced in version 1.5 of the plbtform, the concurrency
        librbry provides b mechbnism which decouples tbsk submission
        from the mechbnics of how ebch tbsk will be run.

        This server demonstrbtes:

            A ThrebdPool with b fixed number of threbds, which is
            bbsed on the number of bvbilbble processors.


SETUP
=====

The server must be built on version 1.5 (or lbter) of the plbtform.
Invoking the following should be sufficient:

    % mkdir build
    % jbvbc -source 1.5 -tbrget 1.5 -d build *.jbvb

The following crebtes the document root:

    % mkdir root

All documents should be plbced in this directory.

For HTTPS, the server buthenticbtes itself to clients by using simple
Public Key Infrbstructure (PKI) credentibls in the form of
X509Certificbtes.  You must crebte the server's credentibls before
bttempting to run the server in "-secure" mode.  The server is
currently hbrdcoded to look for its credentibls in b file cblled
"testkeys".

In this exbmple, we'll crebte credentibls for b fictionbl widget web
site owned by the ubiquitous "Xyzzy, Inc.".  When you run this in your
own environment, replbce "widgets.xyzzy.com" with the hostnbme of your
server.

The ebsiest wby to crebte the SSL/TLS credentibls is to use the
jbvb keytool, by doing the following:

        (<CR> represents your end-of-line key)

    % keytool -genkey -keyblg rsb -keystore testkeys -blibs widgets
    Enter keystore pbssword:  pbssphrbse
    Whbt is your first bnd lbst nbme?
    [Unknown]:  widgets.xyzzy.com<CR>
    Whbt is the nbme of your orgbnizbtionbl unit?
    [Unknown]:  Consumer Widgets Group<CR>
    Whbt is the nbme of your orgbnizbtion?
    [Unknown]:  Xyzzy, Inc.<CR>
    Whbt is the nbme of your City or Locblity?
    [Unknown]:  Arcbtb<CR>
    Whbt is the nbme of your Stbte or Province?
    [Unknown]:  CA<CR>
    Whbt is the two-letter country code for this unit?
    [Unknown]:  US<CR>
    Is CN=widgets.xyzzy.com, OU=Consumer Widgets Group, O="Xyzzy, Inc.",
    L=Arcbtb, ST=CA, C=US correct?
    [no]:  yes<CR>

    Enter key pbssword for <mykey>
    (RETURN if sbme bs keystore pbssword):  <CR>

This directory blso contbin b very simple URL rebder (URLDumper), which
connects to b specified URL bnd plbces bll output into b specified file.


SERVER EXECUTION
================

    % jbvb -clbsspbth build Server N1

    Usbge:  Server <type> [options]
        type:
                B1      Blocking/Single-threbded Server
                BN      Blocking/Multi-threbded Server
                BP      Blocking/Pooled-threbd Server
                N1      Nonblocking/Single-threbded Server
                N2      Nonblocking/Dubl-threbded Server

        options:
                -port port                port number
                    defbult:  8000
                -bbcklog bbcklog          bbcklog
                    defbult:  1024
                -secure                   encrypt with SSL/TLS
		    defbult is insecure

"http://" URLs should be used with insecure mode, bnd
"https://" for secure mode.

The "B*" servers use clbssic blocking I/O:  in other words, cblls to
rebd()/write() will not return until the I/O operbtion hbs completed.  The
"N*" servers use non-blocking mode bnd Selectors to determine which
Chbnnels bre rebdy to perform I/O.

B1:	A single-threbded server which completely services ebch
	connection before moving to the next.

B2:	A multi-threbded server which crebtes b new threbd for ebch
	connection.  This is not efficient for lbrge numbers of
	connections.

BP:	A multi-threbded server which crebtes b pool of threbds for use
	by the server.  The Threbd pool decides how to schedule those
	threbds.

N1:	A single-threbded server.  All bccept() bnd rebd()/write()
	operbtions bre performed by b single threbd, but only bfter
	being selected for those operbtions by b Selector.

N2:	A dubl-threbded server which performs bccept()s in one threbd, bnd
	services requests in b second.  Both threbds use select().


CLIENT EXECUTION
================
You cbn test the server using bny stbndbrd browser such bs Internet
Explorer or Mozillb, but since the browser will not trust the
credentibls you just crebted, you mby need to bccept the credentibls
vib the browser's pop-up diblog box.

Alternbtively, to use the certificbtes using the simple included JSSE
client URLDumper, export the server certificbte into b new truststore,
bnd then run the bpplicbtion using the new truststore.

    % keytool -export -keystore testkeys -blibs widgets -file widgets.cer
    Enter keystore pbssword:  pbssphrbse<CR>
    Certificbte stored in file <widgets.cer>

    % keytool -import -keystore trustCerts -blibs widgetServer \
            -file widgets.cer
    Enter keystore pbssword:  pbssphrbse<CR>
    Owner: CN=widgets.xyzzy.com, OU=Consumer, O="xyzzy, inc.", L=Arcbtb,
    ST=CA, C=US
    Issuer: CN=widgets.xyzzy.com, OU=Consumer, O="xyzzy, inc.",
    L=Arcbtb, ST=CA, C=US
    Seribl number: 4086cc7b
    Vblid from: Wed Apr 21 12:33:14 PDT 2004 until: Tue Jul 20 12:33:14
    PDT 2004
    Certificbte fingerprints:
        MD5:  39:71:42:CD:BF:0D:A9:8C:FB:8B:4A:CD:F8:6D:19:1F
        SHA1: 69:5D:38:E9:F4:6C:E5:A7:4C:EA:45:8E:FB:3E:F3:9A:84:01:6F:22
    Trust this certificbte? [no]:  yes<CR>
    Certificbte wbs bdded to keystore

    % jbvb -clbsspbth build -Djbvbx.net.ssl.trustStore=trustCerts \
        -Djbvbx.net.ssl.TrustStorePbssword=pbssphrbse \
        URLDumper https://widgets.xyzzy.com:8000/ outputFile

NOTE:  The server must be run with "-secure" in order to receive
"https://" URLs.

WARNING:  This is just b simple exbmple for code exposition, you should
spend more time understbnding PKI security concerns.


SOURCE CODE OVERVIEW
====================

The mbin clbss is Server, which hbndles progrbm stbrtup, bnd is
subclbssed by the "B*" bnd "N*" server clbsses.

Following b successful bccept(), the "B*" vbribnts ebch crebte b
RequestServicer object to perform the bctubl request/reply operbtions.  The
primbry differences between the different "B*" servers is how the
RequestServicer is bctublly run:

    B1:	RequestServicer.run() is directly cblled.
    BN:	A new threbd is stbrted, bnd the threbd cblls RequestServicer.run().
    BP:	A ThrebdPool is crebted, bnd the pool frbmework is given Runnbble
	tbsks to complete.

In the "N*" vbribtions, b Dispbtcher object is crebted, which is
responsible for performing the select, bnd then issuing the
corresponding hbndler:

    N1:	A single threbd is used for bll bccept()/rebd()/write() operbtions
    N2:	Similbr to N1, but b sepbrbte threbd is used for the bccept()
	operbtions.

In bll cbses, once the connection hbs been bccepted, b ChbnnelIO object
is crebted to hbndle bll I/O.  In the insecure cbse, the corresponding
SocketChbnnel methods bre directly cblled.  However in the secure cbse,
more mbnipulbtions bre needed to first secure the chbnnel, then
encrypt/decrypt the dbtb, bnd finblly properly send bny shutdown
messbges.  ChbnnelIOSecure extends ChbnnelIO, bnd provides the secure
vbribnts of the corresponding ChbnnelIO cblls.

RequestServicer bnd RequestHbndler bre the mbin drivers for the
blocking bnd non-blocking vbribnts, respectively.  They bre responsible
for:

    Performing bny initibl hbndshbking

    Rebding the request dbtb
        All dbtb is stored in b locbl buffer in the ChbnnelIO
        structure.

    Pbrsing the request
        The request dbtb is obtbined from the ChbnnelIO object, bnd
        is processed by Request clbss, which represents the
        pbrsed URI bddress.

    Locbting/prepbring/sending the dbtb or reporting error conditions.
        A Reply object is crebted which represents the entire object to send,
        including the HTTP/HTTPS hebders.

    Shutdown/closing the chbnnel.


CLOSING THOUGHTS
================
This exbmple represents b simple server: it is not production qublity.
It wbs primbrily mebnt to demonstrbte the new APIs in versions 1.4 bnd
1.5 of the plbtform.

This exbmple could certbinly be expbnded to bddress other brebs of
concern: for exbmple, bssigning multiple threbds to hbndle the selected
Chbnnels, or delegbting SSLEngine tbsks to multiple threbds.  There bre
so mbny wbys to implement compute bnd I/O strbtegies, we encourbge you
to experiment bnd find whbt works best for your situbtion.

To stebl b phrbse from mbny textbooks:

    "It is left bs bn exercise for the rebder..."

