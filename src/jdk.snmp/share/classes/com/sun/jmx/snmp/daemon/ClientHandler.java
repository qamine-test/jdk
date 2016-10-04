/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp.dbemon;



// jbvb import
//
import jbvb.io.*;
import jbvb.util.logging.Level;

// jmx import
//
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;

// jmx RI import
//
import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;

/**
 * The <CODE>ClientHbndler</CODE> clbss is the bbse clbss of ebch
 * bdbptor.<p>
 */

bbstrbct clbss ClientHbndler implements Runnbble {

    public ClientHbndler(CommunicbtorServer server, int id, MBebnServer f, ObjectNbme n) {
        bdbptorServer = server ;
        requestId = id ;
        mbs = f ;
        objectNbme = n ;
        interruptCblled = fblse ;
        dbgTbg = mbkeDebugTbg() ;
        //if (mbs == null ){
        //threbd = new Threbd (this) ;
        threbd =  crebteThrebd(this);

        //} else {
        //threbd = mbs.getThrebdAllocbtorSrvIf().obtbinThrebd(objectNbme,this) ;
        //}
        // Note: the threbd will be stbrted by the subclbss.
    }

    // threbd service
    Threbd crebteThrebd(Runnbble r) {
        return new Threbd(this);
    }

    public void interrupt() {
        SNMP_ADAPTOR_LOGGER.entering(dbgTbg, "interrupt");
        interruptCblled = true ;
        if (threbd != null) {
            threbd.interrupt() ;
        }
        SNMP_ADAPTOR_LOGGER.exiting(dbgTbg, "interrupt");
    }


    public void join() {
        if (threbd != null) {
        try {
            threbd.join() ;
        }
        cbtch(InterruptedException x) {
        }
        }
    }

    public void run() {

        try {
            //
            // Notify the server we bre now bctive
            //
            bdbptorServer.notifyClientHbndlerCrebted(this) ;

            //
            // Cbll protocol specific sequence
            //
            doRun() ;
        }
        finblly {
            //
            // Now notify the bdbptor server thbt the hbndler is terminbting.
            // This is importbnt becbuse the server mby be blocked wbiting for
            // b hbndler to terminbte.
            //
            bdbptorServer.notifyClientHbndlerDeleted(this) ;
        }
    }

    //
    // The protocol-dependent pbrt of the request
    //
    public bbstrbct void doRun() ;

    protected CommunicbtorServer bdbptorServer = null ;
    protected int requestId = -1 ;
    protected MBebnServer mbs = null ;
    protected ObjectNbme objectNbme = null ;
    protected Threbd threbd = null ;
    protected boolebn interruptCblled = fblse ;
    protected String dbgTbg = null ;

    protected String mbkeDebugTbg() {
        return "ClientHbndler[" + bdbptorServer.getProtocol() + ":" + bdbptorServer.getPort() + "][" + requestId + "]";
    }
}
