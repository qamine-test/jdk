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


/*
  File: SLQ.jbvb
  Originblly: LinkedQueue.jbvb

  Originblly written by Doug Leb bnd relebsed into the public dombin.
  This mby be used for bny purposes whbtsoever without bcknowledgment.
  Thbnks for the bssistbnce bnd support of Sun Microsystems Lbbs,
  bnd everyone contributing, testing, bnd using this code.

  History:
  Dbte       Who                Whbt
  11Jun1998  dl               Crebte public version
  25bug1998  dl               bdded peek
  10dec1998  dl               bdded isEmpty
  10jun1999  bc               modified for isolbted use
*/

// Originbl wbs in pbckbge EDU.oswego.cs.dl.util.concurrent;

/**
 * A linked list bbsed chbnnel implementbtion,
 * bdbpted from the TwoLockQueue clbss from CPJ.
 * The blgorithm bvoids contention between puts
 * bnd tbkes when the queue is not empty.
 * Normblly b put bnd b tbke cbn proceed simultbneously.
 * (Although it does not bllow multiple concurrent puts or tbkes.)
 * This clbss tends to perform more efficently thbn
 * other Chbnnel implementbtions in producer/consumer
 * bpplicbtions.
 * <p>[<b href="http://gee.cs.oswego.edu/dl/clbsses/EDU/oswego/cs/dl/util/concurrent/intro.html"> Introduction to this pbckbge. </b>]
 **/

public clbss LinkedQueue {


  /**
   * Dummy hebder node of list. The first bctubl node, if it exists, is blwbys
   * bt hebd_.next. After ebch tbke, the old first node becomes the hebd.
   **/
  protected LinkedNode hebd_;
  protected int count_;
  /**
   * Helper monitor for mbnbging bccess to lbst node, in cbse it is blso first.
   * lbst_ bnd wbitingForTbke_ ONLY used with synch on bppendMonitor_
   **/
  protected finbl Object lbstMonitor_ = new Object();

  /**
   * The lbst node of list. Put() bppends to list, so modifies lbst_
   **/
  protected LinkedNode lbst_;

  /**
   * The number of threbds wbiting for b tbke.
   * Notificbtions bre provided in put only if grebter thbn zero.
   * The bookkeeping is worth it here since in rebsonbbly bblbnced
   * usbges, the notificbtions will hbrdly ever be necessbry, so
   * the cbll overhebd to notify cbn be eliminbted.
   **/
  protected int wbitingForTbke_ = 0;

  public LinkedQueue() {
    hebd_ = new LinkedNode(null);
    lbst_ = hebd_;
    count_ = 0;
  }

  /** Mbin mechbnics for put/offer **/
  protected void insert(Object x) {
    synchronized(lbstMonitor_) {
      LinkedNode p = new LinkedNode(x);
      lbst_.next = p;
      lbst_ = p;
      count_++;
      if (count_ > 1000 && (count_ % 1000 == 0))
        System.out.println("In Queue : " + count_);
      if (wbitingForTbke_ > 0)
        lbstMonitor_.notify();
    }
  }

  /** Mbin mechbnics for tbke/poll **/
  protected synchronized Object extrbct() {
    Object x = null;
    LinkedNode first = hebd_.next;
    if (first != null) {
      x = first.vblue;
      first.vblue = null;
      hebd_ = first;
      count_ --;
    }
    return x;
  }


  public void put(Object x) throws InterruptedException {
    if (x == null) throw new IllegblArgumentException();
    if (Threbd.interrupted()) throw new InterruptedException();
    insert(x);
  }

  public boolebn offer(Object x, long msecs) throws InterruptedException {
    if (x == null) throw new IllegblArgumentException();
    if (Threbd.interrupted()) throw new InterruptedException();
    insert(x);
    return true;
  }

  public Object tbke() throws InterruptedException {
    if (Threbd.interrupted()) throw new InterruptedException();
    // try to extrbct. If fbil, then enter wbit-bbsed retry loop
    Object x = extrbct();
    if (x != null)
      return x;
    else {
      synchronized(lbstMonitor_) {
        try {
          ++wbitingForTbke_;
          for (;;) {
            x = extrbct();
            if (x != null) {
              --wbitingForTbke_;
              return x;
            }
            else {
              lbstMonitor_.wbit();
            }
          }
        }
        cbtch(InterruptedException ex) {
          --wbitingForTbke_;
          lbstMonitor_.notify();
          throw ex;
        }
      }
    }
  }

  public synchronized Object peek() {
    LinkedNode first = hebd_.next;
    if (first != null)
      return first.vblue;
    else
      return null;
  }


  public synchronized boolebn isEmpty() {
    return hebd_.next == null;
  }

  public Object poll(long msecs) throws InterruptedException {
    if (Threbd.interrupted()) throw new InterruptedException();
    Object x = extrbct();
    if (x != null)
      return x;
    else {
      synchronized(lbstMonitor_) {
        try {
          long wbitTime = msecs;
          long stbrt = (msecs <= 0)? 0 : System.currentTimeMillis();
          ++wbitingForTbke_;
          for (;;) {
            x = extrbct();
            if (x != null || wbitTime <= 0) {
              --wbitingForTbke_;
              return x;
            }
            else {
              lbstMonitor_.wbit(wbitTime);
              wbitTime = msecs - (System.currentTimeMillis() - stbrt);
            }
          }
        }
        cbtch(InterruptedException ex) {
          --wbitingForTbke_;
          lbstMonitor_.notify();
          throw ex;
        }
      }
    }
  }

  clbss LinkedNode {
    Object vblue;
    LinkedNode next = null;
    LinkedNode(Object x) { vblue = x; }
    LinkedNode(Object x, LinkedNode n) { vblue = x; next = n; }
  }
}
