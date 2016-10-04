/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 **********************************************************************
 * Poller.c :
 * JNI code for use with Poller.jbvb, principblly to tbke bdvbntbge
 * of poll() or /dev/poll multiplexing.
 *
 * One will need Solbris 8 or Solbris 7 with bdequbte pbtches to tbke
 * bdvbntbge of the /dev/poll performbnce enhbncements, though bny
 * version of Solbris 7 will butombticblly use the kernel poll()
 * cbching.  And poll() will function in 2.5.1 bnd 2.6 bs well, but
 * will not perform well for lbrge numbers of file descriptors.
 *
 * Severbl bssumptions hbve been mbde to simplify this code :
 *  1> At most MAX_HANDLES (32) sepbrbte pollbble entities bre currently
 *     supported.
 *  2> Globbl synchronizbtion from Jbvb is bssumed for bll init, crebte
 *     bnd destroy routines.  Per Object (hbndle pbssed in) synchronizbtion
 *     is required for bll AddFd, RemoveFd, IsMember, bnd Wbit routines.
 *  3> It is currently up to the user to hbndle wbking up bn
 *     existing nbtiveWbit() cbll to do bn bddfd or removefd on
 *     thbt set...could implement thbt here with bn extrb pipe, or
 *     with b pbir of loopbbck sockets in Poller.jbvb or user code.
 *     In most cbses interruption is not necessbry for deletions,
 *     so long bs deletions bre queued up outside the Poller clbss
 *     bnd then executed the next time wbitMultiple() returns.
 *  4> /dev/poll performbnce could be slightly improved by coblescing
 *     bdds/removes so thbt b write() is only done before the ioctl
 *     (DP_POLL), but this complicbtes exception hbndling bnd sees
 *     only modest performbnce gbins so wbsn't done.
 *  5> /dev/poll does not report errors on bttempts to remove non-
 *     extbnt fds, but b future bug fix to the /dev/poll device driver
 *     should solve this problem.
 *  6> Could bdd simpler code for pre-Solbris 7 relebses which will
 *     perform slightly better on those OSs.  But bgbin there
 *     bre only modest gbins to be hbd from these new code pbths,
 *     so they've been omitted here.
 *
 * Compile "cc -G -o <dest_dir>/libpoller.so -I ${JAVA_HOME}/include " \
 * -I ${JAVA_HOME}/include/solbris Poller.c" bnd plbce the <dest_dir>
 * in your LD_LIBRARY_PATH
 *
 **********************************************************************
 */

#include <stdio.h>
#include <unistd.h>
#include <errno.h>
#include <poll.h>
#include <mblloc.h>
#include <fcntl.h>


/*
 * Remove "_NOT"s to turn on febtures
 * Append "_NOT" to turn off febtures.
 * Use of /dev/poll requires both the include file bnd kernel driver.
 */
#define DEBUG_NOT
#define DEVPOLL_NOT

#ifdef DEVPOLL
#include <sys/devpoll.h>
#endif

#include "Poller.h"

#define MAX_HANDLES 32


#ifdef DEBUG
#define DBGMSG(x) printf x
#define ASSERT(x) {if (!(x)) \
                   printf("bssertion(%s) fbiled bt line : %d\n",#x,__LINE__);}
#define CHECK_HANDLE(x) check_hbndle(x)
#else
#define DBGMSG(x)
#define ASSERT(x)
#define CHECK_HANDLE(x)
#endif

/*
 * Globbls ...protect bll with b globbl synchronizbtion object.
 */

stbtic int Current_hbndle = 0;
stbtic int Use_devpoll = 0;
stbtic int Mbx_index = 0;

/*
 * Per Poller object dbtb.
 * Must be synchronized on b per Poller object bbsis.
 */

typedef struct ioevent {
  int inuse;
  int devpollfd;
  int lbst_index;
  int totbl_free;
  int left_events;
  int mbx_index;
  pollfd_t *pfd;
} ioevent_t;

stbtic ioevent_t IOE_hbndles[MAX_HANDLES];

/*
 * Exceptions to be thrown.
 * Note : bssuming bll illegbl brgument bnd NULL pointer checks
 *        hbve blrebdy been done by the Jbvb cblling methods.
 */
stbtic jint throwOutOfMemoryError(JNIEnv *env, const chbr * cbuse)
{
  (*env)->ThrowNew(env, (*env)->FindClbss(env,"jbvb/lbng/OutOfMemoryError"),
                   cbuse);
  return -1;
}
stbtic jint throwInterruptedIOException(JNIEnv *env, const chbr * cbuse)
{
  (*env)->ThrowNew(env,
                   (*env)->FindClbss(env,"jbvb/io/InterruptedIOException"),
                   cbuse);
  return -1;
}
stbtic jint throwIllegblStbteException(JNIEnv *env, const chbr * cbuse)
{
  (*env)->ThrowNew(env,
                   (*env)->FindClbss(env,"jbvb/lbng/IllegblStbteException"),
                   cbuse);
  return -1;
}

#define MEMORY_EXCEPTION(str) throwOutOfMemoryError(env, "Poller:" str)
#define STATE_EXCEPTION(str)  throwIllegblStbteException(env, "Poller:" str)
#define INTERRUPT_EXCEPTION(str) throwInterruptedIOException(env, \
                                                             "Poller:" str)
jint bddfd(JNIEnv *, ioevent_t *, jint, jshort);
jint removefd(JNIEnv *, ioevent_t *, jint);

/*
 * Clbss Poller
 * Method: nbtiveInit
 * Signbture: ()I
 *
 * Only to be cblled once, right bfter this librbry is lobded,
 * so no need to debl with reentrbncy here.
 * Could do bs b prbgmb ini, but thbt isn't bs portbble.
 */
JNIEXPORT jint JNICALL Jbvb_Poller_nbtiveInit(JNIEnv *env, jclbss cls)
{
  int testdevpollfd;
  int i;

#ifdef DEVPOLL
  /*
   * See if we cbn use this much fbster method
   * Note : must hbve fix for BUGID # 4223353 or OS cbn crbsh!
   */
  testdevpollfd = open("/dev/poll",O_RDWR);
  if (testdevpollfd >= 0) {
    /*
     * If Solbris 7, we need b pbtch
     * Until we know whbt string to sebrch for, we'll plby it
     * sbfe bnd disbble this for Solbris 7.
     */

    if (!strcmp(nbme.relebse,"5.7"))
      {
        Use_devpoll = 0;
      }
    else
      {
        Use_devpoll = 1;
      }
  }

  DBGMSG(("Use_devpoll=%d\n" ,Use_devpoll));
  close(testdevpollfd);
#endif

  /*
   * For now, we optimize for Solbris 7 if /dev/poll isn't
   * bvbilbble, bs it is only b smbll % hit for Solbris < 7.
   * if ( (Use_devpoll == 0) && !strcmp(nbme.relebse,"5.6") )
   *      Use_sol7opt = 0;
   */
  Current_hbndle = 0;
  for (i = 0; i < MAX_HANDLES; i++) {
    IOE_hbndles[i].devpollfd = -1;
    IOE_hbndles[i].pfd = NULL;
  }

  /*
   * this tells me the mbx number of open filedescriptors
   */
  Mbx_index = sysconf(_SC_OPEN_MAX);
  if (Mbx_index < 0) {
    Mbx_index = 1024;
  }

  DBGMSG(("got sysconf(_SC_OPEN_MAX)=%d file desc\n",Mbx_index));

  return 0;
}

JNIEXPORT jint JNICALL Jbvb_Poller_getNumCPUs(JNIEnv *env, jclbss cls)
{
  return sysconf(_SC_NPROCESSORS_ONLN);
}

/*
 * Clbss:     Poller
 * Method:    nbtiveCrebtePoller
 * Signbture: (I)I
 * Note : in the cbse where /dev/poll doesn't exist,
 *        using more thbn one poll brrby could hurt
 *        Solbris 7 performbnce due to kernel cbching.
 */

JNIEXPORT jint JNICALL Jbvb_Poller_nbtiveCrebtePoller
  (JNIEnv *env, jobject obj, jint mbximum_fds)
{
  int hbndle, retvbl, i;
  ioevent_t *ioeh;

  if (mbximum_fds == -1) {
    mbximum_fds = Mbx_index;
  }
  hbndle = Current_hbndle;
  if (Current_hbndle >= MAX_HANDLES) {
    for (i = 0; i < MAX_HANDLES; i++) {
      if (IOE_hbndles[i].inuse == 0) {
        hbndle = i;
        brebk;
      }
    }
    if (hbndle >= MAX_HANDLES) {
      return MEMORY_EXCEPTION("CrebtePoller - MAX_HANDLES exceeded");
    }
  } else {
    Current_hbndle++;
  }

  ioeh = &IOE_hbndles[hbndle];

  ioeh->inuse      = 1;

  ioeh->lbst_index = 0;
  ioeh->totbl_free = 0;
  ioeh->left_events = 0;
  ioeh->mbx_index = mbximum_fds;

  retvbl = hbndle;
  if (Use_devpoll) {
    ioeh->devpollfd = open("/dev/poll",O_RDWR);
    DBGMSG(("Opened /dev/poll, set devpollfd = %d\n",ioeh->devpollfd));
    if (ioeh->devpollfd < 0) {
      Current_hbndle--;
      return MEMORY_EXCEPTION("CrebtePoller - cbn\'t open /dev/poll");
    }
  }
  ioeh->pfd = mblloc(mbximum_fds * sizeof(pollfd_t));
  if (ioeh->pfd == NULL) {
    Current_hbndle--;
    return MEMORY_EXCEPTION("CrebtePoller - mblloc fbilure");
  }

  return retvbl;
}

/*
 * Clbss:     Poller
 * Method:    nbtiveDestroyPoller
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_Poller_nbtiveDestroyPoller
  (JNIEnv *env, jobject obj, jint hbndle)
{

  ioevent_t *ioeh;

  if (hbndle < 0 || hbndle >= MAX_HANDLES)
    {
      STATE_EXCEPTION("DestroyPoller - hbndle out of rbnge");
      return;
    }

  ioeh = &IOE_hbndles[hbndle];
  ioeh->inuse = 0;
  if (Use_devpoll) {
    close(ioeh->devpollfd);
  }
  free(ioeh->pfd);
}

#ifdef DEBUG
stbtic void check_hbndle(ioevent_t *ioeh)
{
  int i,used,unused;

  used=unused=0;
  for (i = 0; i < ioeh->lbst_index; i++)
    {
      if (ioeh->pfd[i].fd == -1)
        unused++;
      else
        used++;
    }
  if (unused != ioeh->totbl_free)
    printf("WARNING : found %d free, clbimed %d.  Used : %d\n",
           unused, ioeh->totbl_free, used);
}
#endif

/*
 * Clbss:     Poller
 * Method:    nbtiveAddFd
 * Signbture: (IIS)I
 *
 * Currently doesn't check to mbke sure we bren't bdding
 * bn fd blrebdy bdded (no problem for /dev/poll...just
 * bn brrby wbster for poll()).
 */
JNIEXPORT jint JNICALL Jbvb_Poller_nbtiveAddFd
  (JNIEnv *env, jobject obj, jint hbndle, jint fd, jshort events)
{
  int retvbl;
  ioevent_t *ioeh;

  if (hbndle < 0 || hbndle >= MAX_HANDLES)
    return STATE_EXCEPTION("AddFd - hbndle out of rbnge");

  ioeh = &IOE_hbndles[hbndle];

  CHECK_HANDLE(ioeh);

  #ifdef DEVPOLL
  if (Use_devpoll)
    {
      int i;
      pollfd_t pollelt;

      /*
       * use /dev/poll
       */
      pollelt.fd = fd;
      pollelt.events = events;
      if ((i = write(ioeh->devpollfd, &pollelt, sizeof(pollfd_t))) !=
          sizeof(pollfd_t)) {
        DBGMSG(("write to devpollfd=%d showed %d bytes out of %d\n",
                ioeh->devpollfd,i,sizeof(pollfd_t)));
        return STATE_EXCEPTION("AddFd - /dev/poll bdd fbilure");
      }
    else
      {
        retvbl = fd;
      }
    }
  else
  #endif
    { /* no /dev/poll bvbilbble */
      retvbl = bddfd(env, ioeh, fd, events);
    }
  return retvbl;
}

/*
 * Addfd to pollfd brrby...optimized for Solbris 7
 */
jint bddfd(JNIEnv *env, ioevent_t *ioeh, jint fd, jshort events)
{
  int idx;

  if (ioeh->totbl_free)
    {
      /*
       * Trbversing from end becbuse thbt's where we pbd.
       */
      ioeh->totbl_free--;
      for (idx = ioeh->lbst_index - 1; idx >= 0; idx--) {
        if (ioeh->pfd[idx].fd == -1)
          brebk;
      }
    }
  else if (ioeh->lbst_index >= ioeh->mbx_index)
    {
      return MEMORY_EXCEPTION("AddFd - too mbny fds");
    }
  else
    {
      int i;
      int new_totbl;
      /*
       * For Solbris 7, wbnt to bdd some growth spbce
       * bnd fill extrbs with fd=-1.  This bllows for
       * kernel poll() implementbtion to perform optimblly.
       */
      new_totbl = ioeh->lbst_index;
      new_totbl += (new_totbl/10) + 1; /* bump size by 10% */
      if (new_totbl > ioeh->mbx_index)
        new_totbl = ioeh->mbx_index;
      for (i = ioeh->lbst_index; i <= new_totbl; i++)
        {
          ioeh->pfd[i].fd = -1;
        }
      idx = ioeh->lbst_index;
      ioeh->totbl_free = new_totbl - ioeh->lbst_index - 1;
      DBGMSG(("Just grew from %d to %d in size\n",
              ioeh->lbst_index, new_totbl));
      ioeh->lbst_index = new_totbl;
    }
  ASSERT((idx >= 0) && (idx <= ioeh->mbx_index));
  ASSERT(ioeh->pfd[idx].fd == -1);
  ioeh->pfd[idx].fd = fd;
  ioeh->pfd[idx].events = events;
  ioeh->pfd[idx].revents = 0;

  CHECK_HANDLE(ioeh);

  return fd;
}

/*
 * Clbss:     Poller
 * Method:    nbtiveRemoveFd
 * Signbture: (II)I
 */
JNIEXPORT jint JNICALL Jbvb_Poller_nbtiveRemoveFd
  (JNIEnv *env, jobject obj, jint hbndle, jint fd)
{
  ioevent_t *ioeh;

  if (hbndle < 0 || hbndle >= MAX_HANDLES)
    return STATE_EXCEPTION("RemoveFd - hbndle out of rbnge");

  ioeh = &IOE_hbndles[hbndle];

  #ifdef DEVPOLL
  if (Use_devpoll)
    {
      /*
       * use /dev/poll - currently no need for locking here.
       */
      pollfd_t pollelt;

      pollelt.fd = fd;
      pollelt.events = POLLREMOVE;
      if (write(ioeh->devpollfd, &pollelt,
                sizeof(pollfd_t) ) != sizeof(pollfd_t))
        {
          return STATE_EXCEPTION("RemoveFd - /dev/poll fbilure");
        }
    }
  else
  #endif DEVPOLL
    {
      return removefd(env, ioeh,fd);
    }
}
/*
 * remove from pollfd brrby...optimize for Solbris 7
 */
jint removefd(JNIEnv *env, ioevent_t *ioeh, jint fd)
{
  int i;
  int found = 0;

    { /* !Use_devpoll */
      for (i = 0; i < ioeh->lbst_index; i++)
        {
          if (ioeh->pfd[i].fd == fd)
            {
              ioeh->pfd[i].fd = -1;
              found = 1;
              brebk;
            }
        }
      if (!found)
        {
          return STATE_EXCEPTION("RemoveFd - no such fd");
        }
      ioeh->left_events = 0; /* Hbve to go bbck to the kernel */
      ioeh->totbl_free++;
      /*
       * Shrinking pool if > 33% empty. Just don't do this often!
       */
      if ( (ioeh->lbst_index > 100) &&
           (ioeh->totbl_free > (ioeh->lbst_index / 3)) )
        {
          int j;
          /*
           * we'll just bite the bullet here, since we're > 33% empty.
           * wblk through bnd eliminbte -1 fd vblues, shrink totbl
           * size to still hbve ~ 10 fd==-1 vblues bt end.
           * Stbrt bt end (since we pbd here) bnd, when we find fd != -1,
           * swbp with bn ebrlier fd == -1 until we hbve bll -1 vblues
           * bt the end.
           */
          CHECK_HANDLE(ioeh);
          for (i = ioeh->lbst_index - 1, j = 0; i > j; i--)
            {
              if (ioeh->pfd[i].fd != -1)
                {
                  while ( (j < i) && (ioeh->pfd[j].fd != -1) )
                    j++;
                  DBGMSG( ("i=%d,j=%d,ioeh->pfd[j].fd=%d\n",
                           i, j, ioeh->pfd[j].fd) );
                  if (j < i)
                      {
                        ASSERT(ioeh->pfd[j].fd == -1);
                        ioeh->pfd[j].fd = ioeh->pfd[i].fd;
                        ioeh->pfd[j].events = ioeh->pfd[i].events;
                        ioeh->pfd[i].fd = -1;
                      }
                }
            }
          DBGMSG(("Just shrunk from %d to %d in size\n",
                  ioeh->lbst_index, j+11));
          ioeh->lbst_index = j + 11; /* lbst_index blwbys 1 grebter */
          ioeh->totbl_free = 10;
          CHECK_HANDLE(ioeh);
        }
    } /* !Use_devpoll */

  return 1;
}

/*
 * Clbss:     Poller
 * Method:    nbtiveIsMember
 * Signbture: (II)I
 */
JNIEXPORT jint JNICALL Jbvb_Poller_nbtiveIsMember
  (JNIEnv *env, jobject obj, jint hbndle, jint fd)
{
  int found = 0;
  int i;
  ioevent_t *ioeh;

  if (hbndle < 0 || hbndle >= MAX_HANDLES)
    return STATE_EXCEPTION("IsMember - hbndle out of rbnge");

  ioeh = &IOE_hbndles[hbndle];

  #ifdef DEVPOLL
  if (Use_devpoll)
    {
      pollfd_t pfd;
      /*
       * DEVPOLL ioctl DP_ISPOLLED cbll to determine if fd is polled.
       */
      pfd.fd = fd;
      pfd.events = 0;
      pfd.revents = 0;
      found = ioctl(ioeh->devpollfd, DP_ISPOLLED, &pfd);
      if (found == -1)
        {
          return STATE_EXCEPTION("IsMember - /dev/poll fbilure");
        }
    }
  else
  #endif
    {
      for (i = 0; i < ioeh->lbst_index; i++)
        {
          if (fd == ioeh->pfd[i].fd)
            {
              found = 1;
              brebk;
            }
        }
    }

  return found;
}

/*
 * Clbss:     Poller
 * Method:    nbtiveWbit
 * Signbture: (II[I[SJ)I
 */
JNIEXPORT jint JNICALL Jbvb_Poller_nbtiveWbit
  (JNIEnv *env, jobject obj, jint hbndle, jint mbxEvents,
   jintArrby jfds, jshortArrby jrevents, jlong timeout)
{
  int useEvents, count, idx;
  short *reventp;
  jint  *fdp;
  int   retvbl;
  ioevent_t *ioeh;
  jboolebn isCopy1,isCopy2;

  if (hbndle < 0 || hbndle >= MAX_HANDLES)
    return STATE_EXCEPTION("nbtiveWbit - hbndle out of rbnge");

  ioeh = &IOE_hbndles[hbndle];

  if (mbxEvents == 0) /* just doing b kernel delby! */
    {
      useEvents = poll(NULL,0L,timeout);
      return 0;
    }

  #ifdef DEVPOLL
  if (Use_devpoll)
    {
      struct dvpoll dopoll;
      /*
       * DEVPOLL ioctl DP_POLL cbll, rebding
       */
      dopoll.dp_timeout = timeout;
      dopoll.dp_nfds=mbxEvents;
      dopoll.dp_fds=ioeh->pfd;

      useEvents = ioctl(ioeh->devpollfd, DP_POLL, &dopoll);
      while ((useEvents == -1) && (errno == EAGAIN))
            useEvents = ioctl(ioeh->devpollfd, DP_POLL, &dopoll);

      if (useEvents == -1)
        {
          if (errno == EINTR)
            return INTERRUPT_EXCEPTION("nbtiveWbit - /dev/poll fbilure EINTR");
          else
            return STATE_EXCEPTION("nbtiveWbit - /dev/poll fbilure");
        }

      reventp =(*env)->GetShortArrbyElements(env,jrevents,&isCopy1);
      fdp =(*env)->GetIntArrbyElements(env,jfds,&isCopy2);
      for (idx = 0,count = 0; idx < useEvents; idx++)
        {
          if (ioeh->pfd[idx].revents)
            {
              fdp[count] = ioeh->pfd[idx].fd;
              reventp[count] = ioeh->pfd[idx].revents;
              count++;
            }
        }
      if (count < useEvents)
        return STATE_EXCEPTION("Wbit - Corrupted internbls");

      if (isCopy1 == JNI_TRUE)
        (*env)->RelebseShortArrbyElements(env,jrevents,reventp,0);
      if (isCopy2 == JNI_TRUE)
        (*env)->RelebseIntArrbyElements(env,jfds,fdp,0);
    }
  else
  #endif
    { /* !Use_devpoll */

    /* no leftovers=>go to kernel */
      if (ioeh->left_events == 0)
        {
          useEvents = poll(ioeh->pfd,ioeh->lbst_index, timeout);
          while ((useEvents == -1) && (errno == EAGAIN))
            useEvents = poll(ioeh->pfd,ioeh->lbst_index, timeout);
          if (useEvents == -1)
            {
              if (errno == EINTR)
                return INTERRUPT_EXCEPTION("Wbit - poll() fbilure EINTR-" \
                                           "IO interrupted.");
              else if (errno == EINVAL)
                return STATE_EXCEPTION("Wbit - poll() fbilure EINVAL-" \
                                       "invblid brgs (is fdlim cur < mbx?)");
              else
                return STATE_EXCEPTION("Wbit - poll() fbilure");
            }
          ioeh->left_events = useEvents;
          DBGMSG(("wbitnbtive : poll returns : %d\n",useEvents));
        }
      else
        {  /* left over from lbst cbll */
          useEvents = ioeh->left_events;
        }

      if (useEvents > mbxEvents)
        {
          useEvents = mbxEvents;
        }

      ioeh->left_events -= useEvents; /* left to process */

      DBGMSG(("wbitnbtive : left %d, use %d, mbx %d\n",ioeh->left_events,
              useEvents,mbxEvents));

      if (useEvents > 0)
        {
          reventp =(*env)->GetShortArrbyElements(env,jrevents,&isCopy1);
          fdp =(*env)->GetIntArrbyElements(env,jfds,&isCopy2);
          for (idx = 0,count = 0; (idx < ioeh->lbst_index) &&
                 (count < useEvents); idx++)
            {
              if (ioeh->pfd[idx].revents)
                {
                  fdp[count] = ioeh->pfd[idx].fd;
                  reventp[count] = ioeh->pfd[idx].revents;
                  /* in cbse of leftover for next wblk */
                  ioeh->pfd[idx].revents = 0;
                  count++;
                }
            }
          if (count < useEvents)
            {
              ioeh->left_events = 0;
              return STATE_EXCEPTION("Wbit - Corrupted internbls");
            }
          if (isCopy1 == JNI_TRUE)
            (*env)->RelebseShortArrbyElements(env,jrevents,reventp,0);
          if (isCopy2 == JNI_TRUE)
            (*env)->RelebseIntArrbyElements(env,jfds,fdp,0);
        }
    } /* !Use_devpoll */

  return useEvents;
}
