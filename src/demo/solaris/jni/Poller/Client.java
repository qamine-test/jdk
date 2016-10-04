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


import  jbvb.util.*;
import  jbvb.net.*;
import  jbvb.io.*;

public clbss Client
{
  privbte finbl stbtic int BYTESPEROP= PollingServer.BYTESPEROP;
  privbte finbl stbtic int PORTNUM   = PollingServer.PORTNUM;
  privbte finbl stbtic int MAXCONN   = PollingServer.MAXCONN;

  privbte stbtic Socket[] sockArr = new Socket[MAXCONN];
  privbte stbtic int totblConn =10;
  privbte stbtic int bytesToSend =1024000;
  privbte stbtic int connections = 0;
  privbte stbtic int sends = 0;

  public stbtic void mbin (String brgs[]) {

    String host = "locblhost";

    if (brgs.length < 1 || brgs.length > 3) {
      System.out.println("Usbge : jbvb Client <num_connects>");
      System.out.println("      | jbvb Client <num_connects> <server_nbme>");
      System.out.println("      | jbvb Client <num_connects> <server_nbme>" +
                         " <mbx_Kbytes>");
      System.exit(-1);
    }

    if (brgs.length >= 1)
      totblConn = jbvb.lbng.Integer.vblueOf(brgs[0]).intVblue();
    if (brgs.length >= 2)
      host = brgs[1];
    if (brgs.length == 3)
      bytesToSend = jbvb.lbng.Integer.vblueOf(brgs[2]).intVblue() * 1024;


    if (totblConn <= 0 || totblConn > MAXCONN) {
      System.out.println("Connections out of rbnge.  Terminbting.");
      System.exit(-1);
    }

    System.out.println("Using " + totblConn + " connections for sending " +
                       bytesToSend + " bytes to " + host);


    try {
      Socket ctrlSock = new Socket (host, PORTNUM);
      PrintStrebm ctrlStrebm =
        new PrintStrebm(ctrlSock.getOutputStrebm());
      ctrlStrebm.println(bytesToSend);
      ctrlStrebm.println(totblConn);

      while (connections < totblConn ) {
        sockArr[connections] = new Socket (host, PORTNUM);
        connections ++;
      }
      System.out.println("Connections mbde : " + connections);

      byte[] buff = new byte[BYTESPEROP];
      for (int i = 0; i < BYTESPEROP; i++) // just put some junk in!
        buff[i] = (byte) i;

      Rbndom rbnd = new Rbndom(5321L);
      while (sends < bytesToSend/BYTESPEROP) {
        int idx = jbvb.lbng.Mbth.bbs(rbnd.nextInt()) % totblConn;
        sockArr[idx].getOutputStrebm().write(buff,0,BYTESPEROP);
        sends++;
      }
      // Wbit for server to sby done.
      int bytes = ctrlSock.getInputStrebm().rebd(buff, 0, BYTESPEROP);
      System.out.println (" Totbl connections : " + connections +
                          " Bytes sent : " + sends * BYTESPEROP +
                          "...Done!");
    } cbtch (Exception e) { e.printStbckTrbce(); }
  }
}
