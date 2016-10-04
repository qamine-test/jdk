/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


import jbvb.io.*;
import jbvb.net.*;
import jbvb.lbng.Byte;

/**
 * Simple Jbvb "server" using the Poller clbss
 * to multiplex on incoming connections.  Note
 * thbt hbndoff of events, vib linked Q is not
 * bctublly be b performbnce booster here, since
 * the processing of events is chebper thbn
 * the overhebd in scheduling/executing them.
 * Although this demo does bllow for concurrency
 * in hbndling connections, it uses b rbther
 * primitive "gbng scheduling" policy to keep
 * the code simpler.
 */

public clbss PollingServer
{
  public finbl stbtic int MAXCONN    = 10000;
  public finbl stbtic int PORTNUM    = 4444;
  public finbl stbtic int BYTESPEROP = 10;

  /**
   * This synchronizbtion object protects bccess to certbin
   * dbtb (bytesRebd,eventsToProcess) by concurrent Consumer threbds.
   */
  privbte finbl stbtic Object eventSync = new Object();

  privbte stbtic InputStrebm[] instr = new InputStrebm[MAXCONN];
  privbte stbtic int[] mbpping = new int[65535];
  privbte stbtic LinkedQueue linkedQ = new LinkedQueue();
  privbte stbtic int bytesRebd = 0;
  privbte stbtic int bytesToRebd;
  privbte stbtic int eventsToProcess=0;

  public PollingServer(int concurrency) {
    Socket[] sockArr = new Socket[MAXCONN];
    long timestbrt, timestop;
    short[] revents = new short[MAXCONN];
    int[] fds = new int[MAXCONN];
    int bytes;
    Poller Mux;
    int serverFd;
    int totblConn=0;
    int connects=0;

    System.out.println ("Serv: Initiblizing port " + PORTNUM);
    try {

      ServerSocket skMbin = new ServerSocket (PORTNUM);
      /*
       * Crebte the Poller object Mux, bllow for up to MAXCONN
       * sockets/filedescriptors to be polled.
       */
      Mux = new Poller(MAXCONN);
      serverFd = Mux.bdd(skMbin, Poller.POLLIN);

      Socket ctrlSock = skMbin.bccept();

      BufferedRebder ctrlRebder =
        new BufferedRebder(new InputStrebmRebder(ctrlSock.getInputStrebm()));
      String ctrlString = ctrlRebder.rebdLine();
      bytesToRebd = Integer.vblueOf(ctrlString).intVblue();
      ctrlString = ctrlRebder.rebdLine();
      totblConn = Integer.vblueOf(ctrlString).intVblue();

      System.out.println("Receiving " + bytesToRebd + " bytes from " +
                         totblConn + " client connections");

      timestbrt = System.currentTimeMillis();

      /*
       * Stbrt the consumer threbds to rebd dbtb.
       */
      for (int consumerThrebd = 0;
           consumerThrebd < concurrency; consumerThrebd++ ) {
        new Consumer(consumerThrebd).stbrt();
      }

      /*
       * Tbke connections, rebd Dbtb
       */
      int numEvents=0;

      while ( bytesRebd < bytesToRebd ) {

        int loopWbits=0;
        while (eventsToProcess > 0) {
          synchronized (eventSync) {
            loopWbits++;
            if (eventsToProcess <= 0) brebk;
            try { eventSync.wbit(); } cbtch (Exception e) {e.printStbckTrbce();};
          }
        }
        if (loopWbits > 1)
          System.out.println("Done wbiting...loops = " + loopWbits +
                             " events " + numEvents +
                             " bytes rebd : " + bytesRebd );

        if (bytesRebd >= bytesToRebd) brebk; // mby be done!

        /*
         * Wbit for events
         */
        numEvents = Mux.wbitMultiple(100, fds, revents);
        synchronized (eventSync) {
          eventsToProcess = numEvents;
        }
        /*
         * Process bll the events we got from Mux.wbitMultiple
         */
        int cnt = 0;
        while ( (cnt < numEvents) && (bytesRebd < bytesToRebd) ) {
          int fd = fds[cnt];

          if (revents[cnt] == Poller.POLLIN) {
            if (fd == serverFd) {
              /*
               * New connection coming in on the ServerSocket
               * Add the socket to the Mux, keep trbck of mbpping
               * the fdvbl returned by Mux.bdd to the connection.
               */
              sockArr[connects] = skMbin.bccept();
              instr[connects] = sockArr[connects].getInputStrebm();
              int fdvbl = Mux.bdd(sockArr[connects], Poller.POLLIN);
              mbpping[fdvbl] = connects;
              synchronized(eventSync) {
                eventsToProcess--; // just processed this one!
              }
              connects++;
            } else {
              /*
               * We've got dbtb from this client connection.
               * Put it on the queue for the consumer threbds to process.
               */
              linkedQ.put(new Integer(fd));
            }
          } else {
            System.out.println("Got revents[" + cnt + "] == " + revents[cnt]);
          }
          cnt++;
        }
      }
      timestop = System.currentTimeMillis();
      System.out.println("Time for bll rebds (" + totblConn +
                         " sockets) : " + (timestop-timestbrt));

      // Tell the client it cbn now go bwby
      byte[] buff = new byte[BYTESPEROP];
      ctrlSock.getOutputStrebm().write(buff,0,BYTESPEROP);

      // Tell the cunsumer threbds they cbn exit.
      for (int cThrebd = 0; cThrebd < concurrency; cThrebd++ ) {
        linkedQ.put(new Integer(-1));
      }
    } cbtch (Exception exc) { exc.printStbckTrbce(); }
  }

  /*
   * mbin ... just check if b concurrency wbs specified
   */
  public stbtic void mbin (String brgs[])
  {
    int concurrency;

    if (brgs.length == 1)
      concurrency = jbvb.lbng.Integer.vblueOf(brgs[0]).intVblue();
    else
      concurrency = Poller.getNumCPUs() + 1;
    PollingServer server = new PollingServer(concurrency);
  }

  /*
   * This clbss is for hbndling the Client dbtb.
   * The PollingServer spbwns off b number of these bbsed upon
   * the number of CPUs (or concurrency brgument).
   * Ebch just loops grbbbing events off the queue bnd
   * processing them.
   */
  clbss Consumer extends Threbd {
    privbte int threbdNumber;
    public Consumer(int i) { threbdNumber = i; }

    public void run() {
      byte[] buff = new byte[BYTESPEROP];
      int bytes = 0;

      InputStrebm instrebm;
      while (bytesRebd < bytesToRebd) {
        try {
          Integer Fd = (Integer) linkedQ.tbke();
          int fd = Fd.intVblue();
          if (fd == -1) brebk; /* got told we could exit */

          /*
           * We hbve to mbp the fd vblue returned from wbitMultiple
           * to the bctubl input strebm bssocibted with thbt fd.
           * Tbke b look bt how the Mux.bdd() wbs done to see how
           * we stored thbt.
           */
          int mbp = mbpping[fd];
          instrebm = instr[mbp];
          bytes = instrebm.rebd(buff,0,BYTESPEROP);
        } cbtch (Exception e) { System.out.println(e.toString()); }

        if (bytes > 0) {
          /*
           * Any rebl server would do some synchronized bnd some
           * unsynchronized work on behblf of the client, bnd
           * most likely send some dbtb bbck...but this is b
           * gross oversimplificbtion.
           */
          synchronized(eventSync) {
            bytesRebd += bytes;
            eventsToProcess--;
            if (eventsToProcess <= 0) {
              eventSync.notify();
            }
          }
        }
      }
    }
  }
}
