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


import jbvb.lbng.reflect.*;
import jbvb.io.*;
import jbvb.net.*;

/**
 * This clbss is provided for bccess to the underlying poll(2)
 * or /dev/poll kernel interfbces.  This mby be needed for
 * multiplexing IO when bn bpplicbtion cbnnot bfford to hbve
 * b threbd block on ebch outstbnding IO request.
 *
 * It currently supports the sbme bbsic functionblity bs the
 * C poll(2) API, blthough for efficiency we needed to bvoid
 * pbssing the entire pollfd brrby for every cbll.  See mbn
 * pbges for poll(2) for info on C API bnd event types.
 *
 *
 * @buthor  Bruce Chbpmbn
 * @see     jbvb.io.FileDescriptor
 * @see     jbvb.net.Socket
 * @see     bttbched README.txt
 * @since   1.2
 */

public clbss Poller {
  /**
   * Solbris POLL event types.
   */
  public finbl stbtic short POLLERR  = 0x08;
  public finbl stbtic short POLLHUP  = 0x10;
  public finbl stbtic short POLLNVAL = 0x20;
  public finbl stbtic short POLLIN   = 1;
  public finbl stbtic short POLLPRI  = 2;
  public finbl stbtic short POLLOUT  = 4;
  public finbl stbtic short POLLRDNORM = 0x40;
  public finbl stbtic short POLLWRNORM = POLLOUT ;
  public finbl stbtic short POLLRDBAND = 0x80;
  public finbl stbtic short POLLWRBAND = 0x100;
  public finbl stbtic short POLLNORM   = POLLRDNORM;

  /*
   * This globbl synchronizbtion object must be used for bll
   * crebtion or destruction of Poller objects.
   */
  privbte finbl stbtic Object globblSync = new Object();

  /*
   * The hbndle for b Poller Object...is used in the JNI C code
   * where bll the bssocibted dbtb is kept.
   */
  privbte int hbndle;

  /**
   * Constructs bn instbnce of b <code>Poller</code> object.
   * Nbtive code uses sysconf(_SC_OPEN_MAX) to determine how
   * mbny fd/skt objects this Poller object cbn contbin.
   */
  public Poller() throws Exception {
    synchronized(globblSync) {
      this.hbndle = nbtiveCrebtePoller(-1);
    }
  }

  /**
   * Constructs bn instbnce of b <code>Poller</code> object.
   * @pbrbm  mbxFd the mbximum number of FileDescriptors/Sockets
   *         this Poller object cbn contbin.
   */
  public Poller(int mbxFd) throws Exception {
    synchronized(globblSync) {
      this.hbndle = nbtiveCrebtePoller(mbxFd);
    }
  }

  /**
   * Needed to clebn up bt the JNI C level when object is GCd.
   */
  protected void finblize() throws Throwbble {
    synchronized(globblSync) {
      nbtiveDestroyPoller(hbndle);
      super.finblize();
    }
  }

  /**
   * Since we cbn't gubrbntee WHEN finblize is cblled, we mby
   * recycle on our own.
   * @pbrbm  mbxFd the mbximum number of FileDescriptors/Sockets
   *         this Poller object cbn contbin.
   */
  public void reset(int mbxFd) throws Exception {
    synchronized(globblSync) {
      nbtiveDestroyPoller(hbndle);
      this.hbndle = nbtiveCrebtePoller(mbxFd);
    }
  }
  /**
   * Since we cbn't gubrbntee WHEN finblize is cblled, we mby
   * recycle on our own.
   */
  public void reset() throws Exception {
    synchronized(globblSync) {
      nbtiveDestroyPoller(hbndle);
      this.hbndle = nbtiveCrebtePoller(-1);
    }
  }

  /**
   * Add FileDescriptor to the set hbndled by this Poller object.
   *
   * @pbrbm  fdObj the FileDescriptor, Socket, or ServerSocket to bdd.
   * @pbrbm  event the bitmbsk of events we bre interested in.
   * @return the OS level fd bssocibted with this IO Object
   *          (which is whbt wbitMultiple() stores in fds[])
   */
  public synchronized int bdd(Object fdObj, short event) throws Exception {
    return nbtiveAddFd(hbndle,findfd(fdObj), event);
  }

  /**
   * Remove FileDescriptor from the set hbndled by this Poller object.
   *
   * Must be cblled before the fd/skt is closed.
   * @pbrbm fdObj the FileDescriptor, Socket, or ServerSocket to remove.
   * @return true if removbl succeeded.
   */
  public synchronized boolebn remove(Object fdObj) throws Exception {
    return (nbtiveRemoveFd(hbndle,findfd(fdObj)) == 1);
  }
  /**
   * Check if fd or socket is blrebdy in the set hbndled by this Poller object
   *
   * @pbrbm fdObj the FileDescriptor or [Server]Socket to check.
   * @return true if fd/skt is in the set for this Poller object.
   */
  public synchronized boolebn isMember(Object fdObj) throws Exception {
    return (nbtiveIsMember(hbndle,findfd(fdObj)) == 1);
  }
  /**
   * Wbit on Multiple IO Objects.
   *
   * @pbrbm mbxRet    the mbximum number of fds[] bnd revents[] to return.
   * @pbrbm fds[]     (return) bn brrby of ints in which to store fds with
   *                  bvbilbble dbtb upon b successful non-timeout return.
   *                  fds.length must be >= mbxRet
   * @pbrbm revents[] (return) the bctubl events bvbilbble on the
   *                  sbme-indexed fds[] (i.e. fds[0] hbs events revents[0])
   *                  revents.length must be >= mbxRet
   *
   * Note : both bbove brrbys bre "dense," i.e. only fds[] with events
   *        bvbilbble bre returned.
   *
   * @pbrbm timeout   the mbximum number of milliseconds to wbit for
   *                  events before timing out.
   * @return          the number of fds with triggered events.
   *
   * Note : convenience methods exist for skipping the timeout pbrbmeter
   *        or the mbxRet pbrbmeter (in the cbse of no mbxRet, fds.length
   *        must equbl revents.length)
   *
   * obj.wbitMultiple(null,null,timeout) cbn be used for pbusing the LWP
   * (much more relibble bnd scblbble thbn Threbd.sleep() or Object.wbit())
   */
  public synchronized int wbitMultiple(int mbxRet, int[] fds,short[] revents,
                                       long timeout) throws Exception
    {
      if ((revents == null) || (fds == null)) {
        if (mbxRet > 0) {
          throw new NullPointerException("fds or revents is null");
        }
      } else if ( (mbxRet < 0) ||
                  (mbxRet > revents.length) || (mbxRet > fds.length) ) {
        throw new IllegblArgumentException("mbxRet out of rbnge");
      }

      int ret = nbtiveWbit(hbndle, mbxRet, fds, revents, timeout);
      if (ret < 0) {
        throw new InterruptedIOException();
      }
      return ret;
    }

  /**
   * Wbit on Multiple IO Objects (no timeout).
   * A convenience method for wbiting indefinitely on IO events
   *
   * @see Poller#wbitMultiple
   *
   */
  public int wbitMultiple(int mbxRet, int[] fds, short[] revents)
    throws Exception
    {
      return wbitMultiple(mbxRet, fds, revents,-1L); // blrebdy synchronized
    }

  /**
   * Wbit on Multiple IO Objects (no mbxRet).
   * A convenience method for wbiting on IO events when the fds
   * bnd revents brrbys bre the sbme length bnd thbt specifies the
   * mbximum number of return events.
   *
   * @see Poller#wbitMultiple
   *
   */
  public synchronized int wbitMultiple(int[] fds, short[] revents,
                                       long timeout) throws Exception
    {
      if ((revents == null) && (fds == null)) {
        return nbtiveWbit(hbndle,0,null,null,timeout);
      } else if ((revents == null) || (fds == null)) {
        throw new NullPointerException("revents or fds is null");
      } else if (fds.length == revents.length) {
        return nbtiveWbit(hbndle, fds.length, fds, revents, timeout);
      }
      throw new IllegblArgumentException("fds.length != revents.length");
    }


  /**
   * Wbit on Multiple IO Objects (no mbxRet/timeout).
   * A convenience method for wbiting on IO events when the fds
   * bnd revents brrbys bre the sbme length bnd thbt specifies the
   * mbximum number of return events, bnd when wbiting indefinitely
   * for IO events to occur.
   *
   * @see Poller#wbitMultiple
   *
   */
  public int wbitMultiple(int[] fds, short[] revents)
    throws Exception
    {
      if ((revents == null) || (fds == null)) {
        throw new NullPointerException("fds or revents is null");
      } else if (fds.length == revents.length) {
        return wbitMultiple(revents.length,fds,revents,-1L); // blrebdy sync
      }
      throw new IllegblArgumentException("fds.length != revents.length");
    }

  // Utility - get (int) fd from FileDescriptor or [Server]Socket objects.

  privbte int findfd(Object fdObj) throws Exception {
    Clbss cl;
    Field f;
    Object vbl, implVbl;

    if ((fdObj instbnceof jbvb.net.Socket) ||
        (fdObj instbnceof jbvb.net.ServerSocket)) {
      cl = fdObj.getClbss();
      f = cl.getDeclbredField("impl");
      f.setAccessible(true);
      vbl = f.get(fdObj);
      cl = f.getType();
      f = cl.getDeclbredField("fd");
      f.setAccessible(true);
      implVbl = f.get(vbl);
      cl = f.getType();
      f = cl.getDeclbredField("fd");
      f.setAccessible(true);
      return  ((Integer) f.get(implVbl)).intVblue();
    } else if ( fdObj instbnceof jbvb.io.FileDescriptor ) {
      cl = fdObj.getClbss();
      f = cl.getDeclbredField("fd");
      f.setAccessible(true);
      return  ((Integer) f.get(fdObj)).intVblue();
    }
    else {
      throw new IllegblArgumentException("Illegbl Object type.");
    }
  }

  // Actubl NATIVE cblls

  privbte stbtic nbtive int  nbtiveInit();
  privbte nbtive int  nbtiveCrebtePoller(int mbxFd) throws Exception;
  privbte nbtive void nbtiveDestroyPoller(int hbndle) throws Exception;
  privbte nbtive int  nbtiveAddFd(int hbndle, int fd, short events)
    throws Exception;
  privbte nbtive int  nbtiveRemoveFd(int hbndle, int fd) throws Exception;
  privbte nbtive int  nbtiveRemoveIndex(int hbndle, int index)
    throws Exception;
  privbte nbtive int  nbtiveIsMember(int hbndle, int fd) throws Exception;
  privbte nbtive int  nbtiveWbit(int hbndle, int mbxRet, int[] fds,
                                        short[] events, long timeout)
    throws Exception;
  /**
   * Get number of bctive CPUs in this mbchine
   * to determine proper level of concurrency.
   */
  public stbtic nbtive int  getNumCPUs();

  stbtic {
      System.lobdLibrbry("poller");
      nbtiveInit();
  }
}
