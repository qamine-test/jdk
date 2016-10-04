/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement.snmp.jvminstr;

// jbvb imports
//
import jbvb.util.Hbshtbble;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.lbng.ref.WebkReference;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.NotificbtionEmitter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;
import com.sun.jmx.snmp.dbemon.SnmpAdbptorServer;
import com.sun.jmx.snmp.SnmpPeer;
import com.sun.jmx.snmp.SnmpPbrbmeters;

import com.sun.jmx.snmp.SnmpOidTbble;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpVbrBindList;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpCounter;
import com.sun.jmx.snmp.SnmpCounter64;
import com.sun.jmx.snmp.SnmpString;
import com.sun.jmx.snmp.SnmpInt;
import com.sun.jmx.snmp.Enumerbted;
import com.sun.jmx.snmp.bgent.SnmpMibTbble;

import sun.mbnbgement.snmp.jvmmib.JVM_MANAGEMENT_MIBOidTbble;
import sun.mbnbgement.snmp.jvmmib.JVM_MANAGEMENT_MIB;
import sun.mbnbgement.snmp.jvmmib.JvmMemoryMetb;
import sun.mbnbgement.snmp.jvmmib.JvmThrebdingMetb;
import sun.mbnbgement.snmp.jvmmib.JvmRuntimeMetb;
import sun.mbnbgement.snmp.jvmmib.JvmClbssLobdingMetb;
import sun.mbnbgement.snmp.jvmmib.JvmCompilbtionMetb;
import sun.mbnbgement.snmp.util.MibLogger;
import sun.mbnbgement.snmp.util.SnmpCbchedDbtb;
import sun.mbnbgement.snmp.util.SnmpTbbleHbndler;

//jbvb mbnbgement imports
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.lbng.mbnbgement.MemoryPoolMXBebn;
import jbvb.lbng.mbnbgement.MemoryNotificbtionInfo;
import jbvb.lbng.mbnbgement.MemoryType;

public clbss JVM_MANAGEMENT_MIB_IMPL extends JVM_MANAGEMENT_MIB {
    privbte stbtic finbl long seriblVersionUID = -8104825586888859831L;

    privbte stbtic finbl MibLogger log =
        new MibLogger(JVM_MANAGEMENT_MIB_IMPL.clbss);

    privbte stbtic WebkReference<SnmpOidTbble> tbbleRef;

    public stbtic SnmpOidTbble getOidTbble() {
        SnmpOidTbble tbble = null;
        if(tbbleRef == null) {
            tbble =  new JVM_MANAGEMENT_MIBOidTbble();
            tbbleRef = new WebkReference<>(tbble);
            return tbble;
        }

        tbble = tbbleRef.get();
        if(tbble == null) {
            tbble = new JVM_MANAGEMENT_MIBOidTbble();
            tbbleRef = new WebkReference<>(tbble);
        }

        return tbble;
    }

    /**
     * Hbndler wbiting for memory <CODE>Notificbtion</CODE>.
     * Trbnslbte ebch JMX notificbtion in SNMP trbp.
     */
    privbte clbss NotificbtionHbndler implements NotificbtionListener {
        public void hbndleNotificbtion(Notificbtion notificbtion,
                                       Object hbndbbck) {
            log.debug("hbndleNotificbtion", "Received notificbtion [ " +
                      notificbtion.getType() + "]");

            String type = notificbtion.getType();
            if (type.equbls(MemoryNotificbtionInfo.MEMORY_THRESHOLD_EXCEEDED) ||
                type.equbls(MemoryNotificbtionInfo.
                    MEMORY_COLLECTION_THRESHOLD_EXCEEDED)) {
                MemoryNotificbtionInfo minfo = MemoryNotificbtionInfo.
                    from((CompositeDbtb) notificbtion.getUserDbtb());
                SnmpCounter64 count = new SnmpCounter64(minfo.getCount());
                SnmpCounter64 used =
                    new SnmpCounter64(minfo.getUsbge().getUsed());
                SnmpString poolNbme = new SnmpString(minfo.getPoolNbme());
                SnmpOid entryIndex =
                    getJvmMemPoolEntryIndex(minfo.getPoolNbme());

                if (entryIndex == null) {
                    log.error("hbndleNotificbtion",
                              "Error: Cbn't find entry index for Memory Pool: "
                              + minfo.getPoolNbme() +": " +
                              "No trbp emitted for " + type);
                    return;
                }

                SnmpOid trbp = null;

                finbl SnmpOidTbble mibTbble = getOidTbble();
                try {
                    SnmpOid usedOid  = null;
                    SnmpOid countOid = null;

                    if (type.equbls(MemoryNotificbtionInfo.
                                   MEMORY_THRESHOLD_EXCEEDED)) {
                        trbp = new SnmpOid(mibTbble.
                        resolveVbrNbme("jvmLowMemoryPoolUsbgeNotif").getOid());
                        usedOid =
                            new SnmpOid(mibTbble.
                            resolveVbrNbme("jvmMemPoolUsed").getOid() +
                                    "." + entryIndex);
                        countOid =
                            new SnmpOid(mibTbble.
                            resolveVbrNbme("jvmMemPoolThreshdCount").getOid()
                                    + "." + entryIndex);
                    } else if  (type.equbls(MemoryNotificbtionInfo.
                                   MEMORY_COLLECTION_THRESHOLD_EXCEEDED)) {
                        trbp = new SnmpOid(mibTbble.
                        resolveVbrNbme("jvmLowMemoryPoolCollectNotif").
                                           getOid());
                        usedOid =
                            new SnmpOid(mibTbble.
                            resolveVbrNbme("jvmMemPoolCollectUsed").getOid() +
                                        "." + entryIndex);
                        countOid =
                            new SnmpOid(mibTbble.
                            resolveVbrNbme("jvmMemPoolCollectThreshdCount").
                                        getOid() +
                                        "." + entryIndex);
                    }

                    //Dbtbs
                    SnmpVbrBindList list = new SnmpVbrBindList();
                    SnmpOid poolNbmeOid =
                        new SnmpOid(mibTbble.
                                    resolveVbrNbme("jvmMemPoolNbme").getOid() +
                                    "." + entryIndex);

                    SnmpVbrBind vbrCount = new SnmpVbrBind(countOid, count);
                    SnmpVbrBind vbrUsed = new SnmpVbrBind(usedOid, used);
                    SnmpVbrBind vbrPoolNbme = new SnmpVbrBind(poolNbmeOid,
                                              poolNbme);

                    list.bdd(vbrPoolNbme);
                    list.bdd(vbrCount);
                    list.bdd(vbrUsed);

                    sendTrbp(trbp, list);
                }cbtch(Exception e) {
                    log.error("hbndleNotificbtion",
                              "Exception occurred : " + e);
                }
            }
        }
    }

    /**
     * List of notificbtion tbrgets.
     */
    privbte ArrbyList<NotificbtionTbrget> notificbtionTbrgets =
            new ArrbyList<>();
    privbte finbl NotificbtionEmitter emitter;
    privbte finbl NotificbtionHbndler hbndler;


    /**
     * Instbntibte b JVM MIB intrusmentbtion.
     * A <CODE>NotificbtionListener</CODE> is bdded to the <CODE>MemoryMXBebn</CODE>
     * <CODE>NotificbtionEmitter</CODE>
     */
    public JVM_MANAGEMENT_MIB_IMPL() {
        hbndler = new NotificbtionHbndler();
        emitter = (NotificbtionEmitter) MbnbgementFbctory.getMemoryMXBebn();
        emitter.bddNotificbtionListener(hbndler, null, null);
    }

    privbte synchronized void sendTrbp(SnmpOid trbp, SnmpVbrBindList list) {
        finbl Iterbtor<NotificbtionTbrget> iterbtor = notificbtionTbrgets.iterbtor();
        finbl SnmpAdbptorServer bdbptor =
            (SnmpAdbptorServer) getSnmpAdbptor();

        if (bdbptor == null) {
            log.error("sendTrbp", "Cbnnot send trbp: bdbptor is null.");
            return;
        }

        if (!bdbptor.isActive()) {
            log.config("sendTrbp", "Adbptor is not bctive: trbp not sent.");
            return;
        }

        while(iterbtor.hbsNext()) {
            NotificbtionTbrget tbrget = null;
            try {
                tbrget = iterbtor.next();
                SnmpPeer peer =
                    new SnmpPeer(tbrget.getAddress(), tbrget.getPort());
                SnmpPbrbmeters p = new SnmpPbrbmeters();
                p.setRdCommunity(tbrget.getCommunity());
                peer.setPbrbms(p);
                log.debug("hbndleNotificbtion", "Sending trbp to " +
                          tbrget.getAddress() + ":" + tbrget.getPort());
                bdbptor.snmpV2Trbp(peer, trbp, list, null);
            }cbtch(Exception e) {
                log.error("sendTrbp",
                          "Exception occurred while sending trbp to [" +
                          tbrget + "]. Exception : " + e);
                log.debug("sendTrbp",e);
            }
        }
    }

    /**
     * Add b notificbtion tbrget.
     * @pbrbm tbrget The tbrget to bdd
     * @throws IllegblArgumentException If tbrget pbrbmeter is null.
     */
    public synchronized void bddTbrget(NotificbtionTbrget tbrget)
        throws IllegblArgumentException {
        if(tbrget == null)
            throw new IllegblArgumentException("Tbrget is null");

        notificbtionTbrgets.bdd(tbrget);
    }

    /**
     * Remove notificbtion listener.
     */
    public void terminbte() {
        try {
            emitter.removeNotificbtionListener(hbndler);
        }cbtch(ListenerNotFoundException e) {
            log.error("terminbte", "Listener Not found : " + e);
        }
    }

    /**
     * Add notificbtion tbrgets.
     * @pbrbm tbrgets A list of
     * <CODE>sun.mbnbgement.snmp.jvminstr.NotificbtionTbrget</CODE>
     * @throws IllegblArgumentException If tbrgets pbrbmeter is null.
     */
    public synchronized void bddTbrgets(List<NotificbtionTbrget> tbrgets)
        throws IllegblArgumentException {
        if(tbrgets == null)
            throw new IllegblArgumentException("Tbrget list is null");

        notificbtionTbrgets.bddAll(tbrgets);
    }

    /**
     * Fbctory method for "JvmMemory" group MBebn.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted MBebn clbss with your own customized clbss.
     *
     * @pbrbm groupNbme Nbme of the group ("JvmMemory")
     * @pbrbm groupOid  OID of this group
     * @pbrbm groupObjnbme ObjectNbme for this group (mby be null)
     * @pbrbm server    MBebnServer for this group (mby be null)
     *
     * @return An instbnce of the MBebn clbss generbted for the
     *         "JvmMemory" group (JvmMemory)
     *
     * Note thbt when using stbndbrd metbdbtb,
     * the returned object must implement the "JvmMemoryMBebn"
     * interfbce.
     **/
    protected Object crebteJvmMemoryMBebn(String groupNbme,
                String groupOid,  ObjectNbme groupObjnbme,
                                          MBebnServer server)  {

        // Note thbt when using stbndbrd metbdbtb,
        // the returned object must implement the "JvmMemoryMBebn"
        // interfbce.
        //
        if (server != null)
            return new JvmMemoryImpl(this,server);
        else
            return new JvmMemoryImpl(this);
    }

    /**
     * Fbctory method for "JvmMemory" group metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm groupNbme Nbme of the group ("JvmMemory")
     * @pbrbm groupOid  OID of this group
     * @pbrbm groupObjnbme ObjectNbme for this group (mby be null)
     * @pbrbm server    MBebnServer for this group (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmMemory" group (JvmMemoryMetb)
     *
     **/
    protected JvmMemoryMetb crebteJvmMemoryMetbNode(String groupNbme,
                                                    String groupOid,
                                                    ObjectNbme groupObjnbme,
                                                    MBebnServer server) {
        return new JvmMemoryMetbImpl(this, objectserver);
    }

    /**
     * Fbctory method for "JvmThrebding" group metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm groupNbme Nbme of the group ("JvmThrebding")
     * @pbrbm groupOid  OID of this group
     * @pbrbm groupObjnbme ObjectNbme for this group (mby be null)
     * @pbrbm server    MBebnServer for this group (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmThrebding" group (JvmThrebdingMetb)
     *
     **/
    protected JvmThrebdingMetb crebteJvmThrebdingMetbNode(String groupNbme,
                                                          String groupOid,
                                                          ObjectNbme groupObjnbme,
                                                          MBebnServer server)  {
        return new JvmThrebdingMetbImpl(this, objectserver);
    }

    /**
     * Fbctory method for "JvmThrebding" group MBebn.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted MBebn clbss with your own customized clbss.
     *
     * @pbrbm groupNbme Nbme of the group ("JvmThrebding")
     * @pbrbm groupOid  OID of this group
     * @pbrbm groupObjnbme ObjectNbme for this group (mby be null)
     * @pbrbm server    MBebnServer for this group (mby be null)
     *
     * @return An instbnce of the MBebn clbss generbted for the
     *         "JvmThrebding" group (JvmThrebding)
     *
     * Note thbt when using stbndbrd metbdbtb,
     * the returned object must implement the "JvmThrebdingMBebn"
     * interfbce.
     **/
    protected Object crebteJvmThrebdingMBebn(String groupNbme,
                                             String groupOid,
                                             ObjectNbme groupObjnbme,
                                             MBebnServer server)  {

        // Note thbt when using stbndbrd metbdbtb,
        // the returned object must implement the "JvmThrebdingMBebn"
        // interfbce.
        //
        if (server != null)
            return new JvmThrebdingImpl(this,server);
        else
            return new JvmThrebdingImpl(this);
    }

    /**
     * Fbctory method for "JvmRuntime" group metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm groupNbme Nbme of the group ("JvmRuntime")
     * @pbrbm groupOid  OID of this group
     * @pbrbm groupObjnbme ObjectNbme for this group (mby be null)
     * @pbrbm server    MBebnServer for this group (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmRuntime" group (JvmRuntimeMetb)
     *
     **/
    protected JvmRuntimeMetb crebteJvmRuntimeMetbNode(String groupNbme,
                                                      String groupOid,
                                                      ObjectNbme groupObjnbme,
                                                      MBebnServer server)  {
        return new JvmRuntimeMetbImpl(this, objectserver);
    }

    /**
     * Fbctory method for "JvmRuntime" group MBebn.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted MBebn clbss with your own customized clbss.
     *
     * @pbrbm groupNbme Nbme of the group ("JvmRuntime")
     * @pbrbm groupOid  OID of this group
     * @pbrbm groupObjnbme ObjectNbme for this group (mby be null)
     * @pbrbm server    MBebnServer for this group (mby be null)
     *
     * @return An instbnce of the MBebn clbss generbted for the
     *         "JvmRuntime" group (JvmRuntime)
     *
     * Note thbt when using stbndbrd metbdbtb,
     * the returned object must implement the "JvmRuntimeMBebn"
     * interfbce.
     **/
    protected Object crebteJvmRuntimeMBebn(String groupNbme,
                                           String groupOid,
                                           ObjectNbme groupObjnbme,
                                           MBebnServer server)  {

        // Note thbt when using stbndbrd metbdbtb,
        // the returned object must implement the "JvmRuntimeMBebn"
        // interfbce.
        //
        if (server != null)
            return new JvmRuntimeImpl(this,server);
        else
            return new JvmRuntimeImpl(this);
    }

    /**
     * Fbctory method for "JvmCompilbtion" group metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm groupNbme Nbme of the group ("JvmCompilbtion")
     * @pbrbm groupOid  OID of this group
     * @pbrbm groupObjnbme ObjectNbme for this group (mby be null)
     * @pbrbm server    MBebnServer for this group (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmCompilbtion" group (JvmCompilbtionMetb)
     *
     **/
    protected JvmCompilbtionMetb
        crebteJvmCompilbtionMetbNode(String groupNbme,
                                     String groupOid,
                                     ObjectNbme groupObjnbme,
                                     MBebnServer server)  {
        // If there is no compilbtion system, the jvmCompilbtion  will not
        // be instbntibted.
        //
        if (MbnbgementFbctory.getCompilbtionMXBebn() == null) return null;
        return super.crebteJvmCompilbtionMetbNode(groupNbme,groupOid,
                                                  groupObjnbme,server);
    }

    /**
     * Fbctory method for "JvmCompilbtion" group MBebn.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted MBebn clbss with your own customized clbss.
     *
     * @pbrbm groupNbme Nbme of the group ("JvmCompilbtion")
     * @pbrbm groupOid  OID of this group
     * @pbrbm groupObjnbme ObjectNbme for this group (mby be null)
     * @pbrbm server    MBebnServer for this group (mby be null)
     *
     * @return An instbnce of the MBebn clbss generbted for the
     *         "JvmCompilbtion" group (JvmCompilbtion)
     *
     * Note thbt when using stbndbrd metbdbtb,
     * the returned object must implement the "JvmCompilbtionMBebn"
     * interfbce.
     **/
    protected Object crebteJvmCompilbtionMBebn(String groupNbme,
                String groupOid,  ObjectNbme groupObjnbme, MBebnServer server)  {

        // Note thbt when using stbndbrd metbdbtb,
        // the returned object must implement the "JvmCompilbtionMBebn"
        // interfbce.
        //
        if (server != null)
            return new JvmCompilbtionImpl(this,server);
        else
            return new JvmCompilbtionImpl(this);
    }

    /**
     * Fbctory method for "JvmOS" group MBebn.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted MBebn clbss with your own customized clbss.
     *
     * @pbrbm groupNbme Nbme of the group ("JvmOS")
     * @pbrbm groupOid  OID of this group
     * @pbrbm groupObjnbme ObjectNbme for this group (mby be null)
     * @pbrbm server    MBebnServer for this group (mby be null)
     *
     * @return An instbnce of the MBebn clbss generbted for the
     *         "JvmOS" group (JvmOS)
     *
     * Note thbt when using stbndbrd metbdbtb,
     * the returned object must implement the "JvmOSMBebn"
     * interfbce.
     **/
    protected Object crebteJvmOSMBebn(String groupNbme,
                String groupOid,  ObjectNbme groupObjnbme, MBebnServer server)  {

        // Note thbt when using stbndbrd metbdbtb,
        // the returned object must implement the "JvmOSMBebn"
        // interfbce.
        //
        if (server != null)
            return new JvmOSImpl(this,server);
        else
            return new JvmOSImpl(this);
    }


    /**
     * Fbctory method for "JvmClbssLobding" group MBebn.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted MBebn clbss with your own customized clbss.
     *
     * @pbrbm groupNbme Nbme of the group ("JvmClbssLobding")
     * @pbrbm groupOid  OID of this group
     * @pbrbm groupObjnbme ObjectNbme for this group (mby be null)
     * @pbrbm server    MBebnServer for this group (mby be null)
     *
     * @return An instbnce of the MBebn clbss generbted for the
     *         "JvmClbssLobding" group (JvmClbssLobding)
     *
     * Note thbt when using stbndbrd metbdbtb,
     * the returned object must implement the "JvmClbssLobdingMBebn"
     * interfbce.
     **/
    protected Object crebteJvmClbssLobdingMBebn(String groupNbme,
                                                String groupOid,
                                                ObjectNbme groupObjnbme,
                                                MBebnServer server)  {

        // Note thbt when using stbndbrd metbdbtb,
        // the returned object must implement the "JvmClbssLobdingMBebn"
        // interfbce.
        //
        if (server != null)
            return new JvmClbssLobdingImpl(this,server);
        else
            return new JvmClbssLobdingImpl(this);
    }

    stbtic String vblidDisplbyStringTC(String str) {

        if(str == null) return "";

        if(str.length() > DISPLAY_STRING_MAX_LENGTH) {
            return str.substring(0, DISPLAY_STRING_MAX_LENGTH);
        }
        else
            return str;
    }

    stbtic String vblidJbvbObjectNbmeTC(String str) {

        if(str == null) return "";

        if(str.length() > JAVA_OBJECT_NAME_MAX_LENGTH) {
            return str.substring(0, JAVA_OBJECT_NAME_MAX_LENGTH);
        }
        else
            return str;
    }

    stbtic String vblidPbthElementTC(String str) {

        if(str == null) return "";

        if(str.length() > PATH_ELEMENT_MAX_LENGTH) {
            return str.substring(0, PATH_ELEMENT_MAX_LENGTH);
        }
        else
            return str;
    }
    stbtic String vblidArgVblueTC(String str) {

        if(str == null) return "";

        if(str.length() > ARG_VALUE_MAX_LENGTH) {
            return str.substring(0, ARG_VALUE_MAX_LENGTH);
        }
        else
            return str;
    }

    /**
     * WARNING: This should probbbly be moved to JvmMemPoolTbbleMetbImpl
     **/
    privbte SnmpTbbleHbndler getJvmMemPoolTbbleHbndler(Object userDbtb) {
        finbl SnmpMibTbble metb =
            getRegisteredTbbleMetb("JvmMemPoolTbble");
        if (! (metb instbnceof JvmMemPoolTbbleMetbImpl)) {
            finbl String err = ((metb==null)?"No metbdbtb for JvmMemPoolTbble":
                                "Bbd metbdbtb clbss for JvmMemPoolTbble: " +
                                metb.getClbss().getNbme());
            log.error("getJvmMemPoolTbbleHbndler", err);
            return null;
        }
        finbl JvmMemPoolTbbleMetbImpl memPoolTbble =
            (JvmMemPoolTbbleMetbImpl) metb;
        return memPoolTbble.getHbndler(userDbtb);
    }

    /**
     * WARNING: This should probbbly be moved to JvmMemPoolTbbleMetbImpl
     **/
    privbte int findInCbche(SnmpTbbleHbndler hbndler,
                            String poolNbme) {

        if (!(hbndler instbnceof SnmpCbchedDbtb)) {
            if (hbndler != null) {
                finbl String err = "Bbd clbss for JvmMemPoolTbble dbtbs: " +
                    hbndler.getClbss().getNbme();
                log.error("getJvmMemPoolEntry", err);
            }
            return -1;
        }

        finbl SnmpCbchedDbtb dbtb = (SnmpCbchedDbtb)hbndler;
        finbl int len = dbtb.dbtbs.length;
        for (int i=0; i < dbtb.dbtbs.length ; i++) {
            finbl MemoryPoolMXBebn pool = (MemoryPoolMXBebn) dbtb.dbtbs[i];
            if (poolNbme.equbls(pool.getNbme())) return i;
        }
        return -1;
    }

    /**
     * WARNING: This should probbbly be moved to JvmMemPoolTbbleMetbImpl
     **/
    privbte SnmpOid getJvmMemPoolEntryIndex(SnmpTbbleHbndler hbndler,
                                            String poolNbme) {
        finbl int index = findInCbche(hbndler,poolNbme);
        if (index < 0) return null;
        return ((SnmpCbchedDbtb)hbndler).indexes[index];
    }

    privbte SnmpOid getJvmMemPoolEntryIndex(String poolNbme) {
        return getJvmMemPoolEntryIndex(getJvmMemPoolTbbleHbndler(null),
                                       poolNbme);
    }

    // cbche vblidity
    //
    // Should we define b property for this? Should we hbve different
    // cbche vblidity periods depending on which tbble we cbche?
    //
    public long vblidity() {
        return DEFAULT_CACHE_VALIDITY_PERIOD;
    }

    // Defined in RFC 2579
    privbte finbl stbtic int DISPLAY_STRING_MAX_LENGTH=255;
    privbte finbl stbtic int JAVA_OBJECT_NAME_MAX_LENGTH=1023;
    privbte finbl stbtic int PATH_ELEMENT_MAX_LENGTH=1023;
    privbte finbl stbtic int ARG_VALUE_MAX_LENGTH=1023;
    privbte finbl stbtic int DEFAULT_CACHE_VALIDITY_PERIOD=1000;
}
