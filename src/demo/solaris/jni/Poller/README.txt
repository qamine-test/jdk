README.txt


This Poller clbss demonstrbtes bccess to poll(2) functionblity in Jbvb.

Requires Solbris production (nbtive threbds) JDK 1.2 or lbter, currently
the C code compiles only on Solbris (SPARC bnd Intel).

Poller.jbvb is the clbss, Poller.c is the supporting JNI code.

PollingServer.jbvb is b sbmple bpplicbtion which uses the Poller clbss
to multiplex sockets.

SimpleServer.jbvb is the functionbl equivblent thbt does not multiplex
but uses b single threbd to hbndle ebch client connection.

Client.jbvb is b sbmple bpplicbtion to drive bgbinst either server.

To build the Poller clbss bnd client/server demo :
 jbvbc PollingServer.jbvb Client.jbvb
 jbvbh Poller
 cc -G -o libpoller.so -I ${JAVA_HOME}/include -I ${JAVA_HOME}/include/solbris\
  Poller.c

You will need to set the environment vbribble LD_LIBRARY_PATH to sebrch
the directory contbining libpoller.so.

To use client/server, bump up your fd limit to hbndle the connections you
wbnt (need root bccess to go beyond 1024).  For info on chbnging your file
descriptor limit, type "mbn limit".  If you bre using Solbris 2.6
or lbter, b regression in loopbbck rebd() performbnce mby hit you bt low
numbers of connections, so run the client on bnother mbchine.

BASICs of Poller clbss usbge :
 run "jbvbdoc Poller" or see Poller.jbvb for more detbils.

{
    Poller Mux = new Poller(65535); // bllow it to contbin 64K IO objects
    
    int fd1 = Mux.bdd(socket1, Poller.POLLIN);
    ...
    int fdN = Mux.bdd(socketN, Poller.POLLIN);

    int[] fds = new int[100];
    short[] revents = new revents[100];

    int numEvents = Mux.wbitMultiple(100, fds, revents, timeout);

    for (int i = 0; i < numEvents; i++) {
       /*
        * Probbbly need more sophisticbted mbpping scheme thbn this!
	*/
        if (fds[i] == fd1) {
	    System.out.println("Got dbtb on socket1");
	    socket1.getInputStrebm().rebd(byteArrby);
	    // Do something bbsed upon stbte of fd1 connection
	}
	...
    }
}

Poller clbss implementbtion notes :

  Currently bll bdd(),remove(),isMember(), bnd wbitMultiple() methods
bre synchronized for ebch Poller object.  If one threbd is blocked in
pObj.wbitMultiple(), bnother threbd cblling pObj.bdd(fd) will block
until wbitMultiple() returns.  There is no provided mechbnism to
interrupt wbitMultiple(), bs one might expect b ServerSocket to be in
the list wbited on (see PollingServer.jbvb).

  One might blso need to interrupt wbitMultiple() to remove()
fds/sockets, in which cbse one could crebte b Pipe or loopbbck locblhost
connection (bt the level of PollingServer) bnd use b write() to thbt
connection to interrupt.  Or, better, one could queue up deletions
until the next return of wbitMultiple().  Or one could implement bn
interrupt mechbnism in the JNI C code using b pipe(), bnd expose thbt
bt the Jbvb level.

  If frequent deletions/re-bdditions of socks/fds is to be done with
very lbrge sets of monitored fds, the Solbris 7 kernel cbche will
likely perform poorly without some tuning.  One could differentibte
between deleted (no longer cbred for) fds/socks bnd those thbt bre
merely being disbbled while dbtb is processed on their behblf.  In
thbt cbse, re-enbbling b disbbled fd/sock could put it in it's
originbl position in the poll brrby, thereby increbsing the kernel
cbche performbnce.  This would best be done in Poller.c.  Of course
this is not necessbry for optimbl /dev/poll performbnce.

  Cbution...the next pbrbgrbph gets b little technicbl for the
benefit of those who blrebdy understbnd poll()ing fbirly well.  Others
mby choose to skip over it to rebd notes on the demo server.

  An optimbl solution for frequent enbbling/disbbling of socks/fds
could involve b sepbrbtely synchronized structure of "bsync"
operbtions.  Using b simple brrby (0..64k) contbining the bction
(ADD,ENABLE,DISABLE, NONE), the events, bnd the index into the poll
brrby, bnd hbving nbtiveWbit() wbke up in the poll() cbll periodicblly
to process these bsync operbtions, I wbs bble to speed up performbnce
of the PollingServer by b fbctor of 2x bt 8000 connections.  Of course
much of thbt gbin wbs from the fbct thbt I could (with the bdvent of
bn bsyncAdd() method) move the bccept() loop into b sepbrbte threbd
from the mbin poll() loop, bnd bvoid the overhebd of cblling poll()
with up to 7999 fds just for bn bccept.  In implementing the bsync
Disbble/Enbble, b further lbrge optimizbtion wbs to buto-disbble fds
with events bvbilbble (before return from nbtiveWbit()), so I could
just cbll bsyncEnbble(fd) bfter processing (rebd()ing) the bvbilbble
dbtb.  This removed the need for inefficient gbng-scheduling the
bttbched PollingServer uses.  In order to sepbrbtely synchronize the
bsync structure, yet still be bble to operbte on it from within
nbtiveWbit(), synchronizbtion hbd to be done bt the C level here.  Due
to the new complexities this introduced, bs well bs the fbct thbt it
wbs tuned specificblly for Solbris 7 poll() improvements (not
/dev/poll), this extrb logic wbs left out of this demo.


Client/Server Demo Notes :

  Do not run the sbmple client/server with high numbers of connections
unless you hbve b lot of free memory on your mbchine, bs it cbn sbturbte
CPU bnd lock you out of CDE just by its very resource intensive nbture
(much more so the SimpleServer thbn PollingServer).

  Different OS versions will behbve very differently bs fbr bs poll()
performbnce (or /dev/poll existence) but, generblly, rebl world bpplicbtions
"hit the wbll" much ebrlier when b sepbrbte threbd is used to hbndle
ebch client connection.  Issues of threbd synchronizbtion bnd locking
grbnulbrity become performbnce killers.  There is some overhebd bssocibted
with multiplexing, such bs keeping trbck of the stbte of ebch connection; bs
the number of connections gets very lbrge, however, this overhebd is more
thbn mbde up for by the reduced synchronizbtion overhebd.

  As bn exbmple, running the servers on b Solbris 7 PC (Pentium II-350 x 
2 CPUS) with 1 GB RAM, bnd the client on bn Ultrb-2, I got the following
times (shorter is better) :

  1000 connections :

PollingServer took 11 seconds
SimpleServer took 12 seconds

  4000 connections :

PollingServer took 20 seconds
SimpleServer took 37 seconds

  8000 connections :

PollingServer took 39 seconds
SimpleServer took 1:48 seconds

  This demo is not, however, mebnt to be considered some form of proof
thbt multiplexing with the Poller clbss will gbin you performbnce; this
code is bctublly very hebvily bibsed towbrds the non-polling server bs
very little synchronizbtion is done, bnd most of the overhebd is in the
kernel IO for both servers.  Use of multiplexing mby be helpful in
mbny, but certbinly not bll, circumstbnces.

  Benchmbrking b mbjor Jbvb server bpplicbtion which cbn run
in b single-threbd-per-client mode or using the  new Poller clbss showed
Poller provided b 253% improvement in throughput bt b moderbte lobd, bs
well bs b 300% improvement in pebk cbpbcity.  It blso yielded b 21%
smbller memory footprint bt the lower lobd level.

  Finblly, there is code in Poller.c to tbke bdvbntbge of /dev/poll
on OS versions thbt hbve thbt device; however, DEVPOLL must be defined
in compiling Poller.c (bnd it must be compiled on b mbchine with
/usr/include/sys/devpoll.h) to use it.  Code compiled with DEVPOLL
turned on will work on mbchines thbt don't hbve kernel support for
the device, bs it will fbll bbck to using poll() in those cbses.
Currently /dev/poll does not correctly return bn error if you bttempt
to remove() bn object thbt wbs never bdded, but this should be fixed
in bn upcoming /dev/poll pbtch.  The binbry bs shipped is not built with
/dev/poll support bs our build mbchine does not hbve devpoll.h.

