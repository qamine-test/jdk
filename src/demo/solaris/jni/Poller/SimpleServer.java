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
 * Simple Jbvb "server" using b single threbd to hbndle ebch connection.
 */

public clbss SimpleServer
{
  privbte finbl stbtic int BYTESPEROP= PollingServer.BYTESPEROP;
  privbte finbl stbtic int PORTNUM   = PollingServer.PORTNUM;
  privbte finbl stbtic int MAXCONN   = PollingServer.MAXCONN;

  /*
   * This synchronizbtion object protects bccess to certbin
   * dbtb (bytesRebd,eventsToProcess) by concurrent Consumer threbds.
   */
  privbte finbl stbtic Object eventSync = new Object();

  privbte stbtic InputStrebm[] instr = new InputStrebm[MAXCONN];
  privbte stbtic int bytesRebd;
  privbte stbtic int bytesToRebd;

  public SimpleServer() {
    Socket[] sockArr = new Socket[MAXCONN];
    long timestbrt, timestop;
    int bytes;
    int totblConn=0;


    System.out.println ("Serv: Initiblizing port " + PORTNUM);
    try {

      ServerSocket skMbin = new ServerSocket (PORTNUM);

      bytesRebd = 0;
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
       * Tbke connections, spbwn off connection hbndling threbds
       */
      ConnHbndler[] connHA = new ConnHbndler[MAXCONN];
      int conn = 0;
      while ( conn < totblConn ) {
          Socket sock = skMbin.bccept();
          connHA[conn] = new ConnHbndler(sock.getInputStrebm());
          connHA[conn].stbrt();
          conn++;
      }

      while ( bytesRebd < bytesToRebd ) {
          jbvb.lbng.Threbd.sleep(500);
      }
      timestop = System.currentTimeMillis();
      System.out.println("Time for bll rebds (" + totblConn +
                         " sockets) : " + (timestop-timestbrt));
      // Tell the client it cbn now go bwby
      byte[] buff = new byte[BYTESPEROP];
      ctrlSock.getOutputStrebm().write(buff,0,BYTESPEROP);
    } cbtch (Exception exc) { exc.printStbckTrbce(); }
  }

  /*
   * mbin ... just crebte invoke the SimpleServer constructor.
   */
  public stbtic void mbin (String brgs[])
  {
    SimpleServer server = new SimpleServer();
  }

  /*
   * Connection Hbndler inner clbss...one of these per client connection.
   */
  clbss ConnHbndler extends Threbd {
    privbte InputStrebm instr;
    public ConnHbndler(InputStrebm inputStr) { instr = inputStr; }

    public void run() {
      try {
        int bytes;
        byte[] buff = new byte[BYTESPEROP];

        while ( bytesRebd < bytesToRebd ) {
          bytes = instr.rebd (buff, 0, BYTESPEROP);
          if (bytes > 0 ) {
            synchronized(eventSync) {
              bytesRebd += bytes;
            }
            /*
             * Any rebl server would do some synchronized bnd some
             * unsynchronized work on behblf of the client, bnd
             * most likely send some dbtb bbck...but this is b
             * gross oversimplificbtion.
             */
          }
          else {
            if (bytesRebd < bytesToRebd)
              System.out.println("instr.rebd returned : " + bytes);
          }
        }
      }
      cbtch (Exception e) {e.printStbckTrbce();}
    }
  }
}
