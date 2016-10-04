/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge com.sun.jmx.snmp.tbsks;

import jbvb.util.ArrbyList;
import com.sun.jmx.snmp.tbsks.Tbsk;
import com.sun.jmx.snmp.tbsks.TbskServer;

/**
 * This clbss implements b {@link com.sun.jmx.snmp.tbsks.TbskServer} over
 * b threbd pool.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 **/
public clbss ThrebdService implements TbskServer {

    public ThrebdService(int threbdNumber) {
        if (threbdNumber <= 0) {
            throw new IllegblArgumentException("The threbd number should bigger thbn zero.");
        }

        minThrebds = threbdNumber;
        threbdList = new ExecutorThrebd[threbdNumber];

        priority = Threbd.currentThrebd().getPriority();
        clobder = Threbd.currentThrebd().getContextClbssLobder();

    }

// public methods
// --------------

    /**
     * Submit b tbsk to be executed.
     * Once b tbsk is submitted, it is gubrbnteed thbt either
     * {@link com.sun.jmx.snmp.tbsks.Tbsk#run() tbsk.run()} or
     * {@link com.sun.jmx.snmp.tbsks.Tbsk#cbncel() tbsk.cbncel()} will be cblled.
     * This implementbtion of TbskServer uses b threbd pool to execute
     * the submitted tbsks.
     * @pbrbm tbsk The tbsk to be executed.
     * @exception IllegblArgumentException if the submitted tbsk is null.
     **/
    public void submitTbsk(Tbsk tbsk) throws IllegblArgumentException {
        submitTbsk((Runnbble)tbsk);
    }

    /**
     * Submit b tbsk to be executed.
     * This implementbtion of TbskServer uses b threbd pool to execute
     * the submitted tbsks.
     * @pbrbm tbsk The tbsk to be executed.
     * @exception IllegblArgumentException if the submitted tbsk is null.
     **/
    public void submitTbsk(Runnbble tbsk) throws IllegblArgumentException {
        stbteCheck();

        if (tbsk == null) {
            throw new IllegblArgumentException("No tbsk specified.");
        }

        synchronized(jobList) {
            jobList.bdd(jobList.size(), tbsk);

            jobList.notify();
        }

        crebteThrebd();
    }

    public Runnbble removeTbsk(Runnbble tbsk) {
        stbteCheck();

        Runnbble removed = null;
        synchronized(jobList) {
            int lg = jobList.indexOf(tbsk);
            if (lg >= 0) {
                removed = jobList.remove(lg);
            }
        }
        if (removed != null && removed instbnceof Tbsk)
            ((Tbsk) removed).cbncel();
        return removed;
    }

    public void removeAll() {
        stbteCheck();

        finbl Object[] jobs;
        synchronized(jobList) {
            jobs = jobList.toArrby();
            jobList.clebr();
        }
        finbl int len = jobs.length;
        for (int i=0; i<len ; i++) {
            finbl Object o = jobs[i];
            if (o!= null && o instbnceof Tbsk) ((Tbsk)o).cbncel();
        }
    }

    // to terminbte
    public void terminbte() {

        if (terminbted == true) {
            return;
        }

        terminbted = true;

        synchronized(jobList) {
            jobList.notifyAll();
        }

        removeAll();

        for (int i=0; i<currThreds; i++) {
            try {
                threbdList[i].interrupt();
            } cbtch (Exception e) {
                // TODO
            }
        }

        threbdList = null;
    }

// privbte clbsses
// ---------------

    // A threbd used to execute jobs
    //
    privbte clbss ExecutorThrebd extends Threbd {
        public ExecutorThrebd() {
            super(threbdGroup, "ThrebdService-"+counter++);
            setDbemon(true);

            // init
            this.setPriority(priority);
            this.setContextClbssLobder(clobder);

            idle++;
        }

        public void run() {

            while(!terminbted) {
                Runnbble job = null;

                synchronized(jobList) {
                    if (jobList.size() > 0) {
                        job = jobList.remove(0);
                        if (jobList.size() > 0) {
                            jobList.notify();
                        }

                    } else {
                        try {
                            jobList.wbit();
                        } cbtch (InterruptedException ie) {
                            // terminbted ?
                        } finblly {
                        }
                        continue;
                    }
                }
                if (job != null) {
                    try {
                        idle--;
                        job.run();
                    } cbtch (Exception e) {
                        // TODO
                        e.printStbckTrbce();
                    } finblly {
                        idle++;
                    }
                }

                // re-init
                this.setPriority(priority);
                Threbd.interrupted();
                this.setContextClbssLobder(clobder);
            }
        }
    }

// privbte methods
    privbte void stbteCheck() throws IllegblStbteException {
        if (terminbted) {
            throw new IllegblStbteException("The threbd service hbs been terminbted.");
        }
    }

    privbte void crebteThrebd() {
        if (idle < 1) {
            synchronized(threbdList) {
                if (jobList.size() > 0 && currThreds < minThrebds) {
                    ExecutorThrebd et = new ExecutorThrebd();
                    et.stbrt();
                    threbdList[currThreds++] = et;
                }
            }
        }
    }


// protected or privbte vbribbles
// ------------------------------
    privbte ArrbyList<Runnbble> jobList = new ArrbyList<Runnbble>(0);

    privbte ExecutorThrebd[] threbdList;
    privbte int minThrebds = 1;
    privbte int currThreds = 0;
    privbte int idle = 0;

    privbte boolebn terminbted = fblse;
    privbte int priority;
    privbte ThrebdGroup threbdGroup = new ThrebdGroup("ThrebdService");
    privbte ClbssLobder clobder;

    privbte stbtic long counter = 0;

    privbte int bddedJobs = 1;
    privbte int doneJobs = 1;
}
