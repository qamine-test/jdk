/*
 * Copyright (c) 2001, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jmx.snmp.internbl;

import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.util.Hbshtbble;
import jbvb.util.logging.Level;
import jbvb.io.Seriblizbble;

import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpEngineId;
import com.sun.jmx.snmp.SnmpEngine;
import com.sun.jmx.snmp.SnmpUsmKeyHbndler;
import com.sun.jmx.snmp.SnmpEngineFbctory;
import com.sun.jmx.snmp.SnmpUnknownModelException;

import com.sun.jmx.snmp.internbl.SnmpTools;
import com.sun.jmx.snmp.SnmpBbdSecurityLevelException;
import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_LOGGER;

/**
 * This engine is conformbnt with the RFC 2571. It is the mbin object within
 * bn SNMP entity (bgent, mbnbger...).
 * To bn engine is bssocibted bn {@link com.sun.jmx.snmp.SnmpEngineId}.
 * The wby the engineId is retrieved is linked to the wby the engine is
 * instbntibted. See ebch <CODE>SnmpEngine</CODE> constructor for more detbils.
 * An engine is composed of b set of sub systems
 * {@link com.sun.jmx.snmp.internbl.SnmpSubSystem}. An <CODE>SNMP</CODE>
 * engine cbn contbin b:
 *<ul>
 *<li> Messbge Processing Sub System :
 * {@link com.sun.jmx.snmp.internbl.SnmpMsgProcessingSubSystem}</li>
 *<li> Security Sub System :
 * {@link com.sun.jmx.snmp.internbl.SnmpSecuritySubSystem} </li>
 *<li> Access Control Sub System :
 * {@link com.sun.jmx.snmp.internbl.SnmpAccessControlSubSystem}</li>
 *</ul>
 *<P> Ebch sub system contbins b set of models. A model is bn implementbtion
 * of b pbrticulbr trebtement (eg: the User bbsed Security Model defined in
 * RFC 2574 is b functionbl element debling with buthenticbtion bnd privbcy).
 *</P>
 * Engine instbntibtion is bbsed on b fbctory. This fbctory, implementing
 * mbndbtorily {@link com.sun.jmx.snmp.SnmpEngineFbctory  SnmpEngineFbctory}
 * is set in the method <CODE>setFbctory</CODE>.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public clbss SnmpEngineImpl implements SnmpEngine, Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -2564301391365614725L;

    /**
     * Security level. No buthenticbtion, no privbcy. Vblue is 0,
     * bs defined in RFC 2572
     */
    public stbtic finbl int noAuthNoPriv = 0;
    /**
     * Security level. Authenticbtion, no privbcy. Vblue is 1, bs
     * defined in RFC 2572
     */
    public stbtic finbl int buthNoPriv = 1;
    /**
     * Security level. Authenticbtion, privbcy. Vblue is 3,
     * bs defined in RFC 2572
     */
    public stbtic finbl int buthPriv = 3;
    /**
     * Flbg thbt indicbtes thbt b report is to be sent. Vblue is 4, bs defined in RFC 2572
     */
    public stbtic finbl int reportbbleFlbg = 4;

    /**
     * Mbsk used to isolbte buthenticbtion informbtion within b messbge flbg.
     */
    public stbtic finbl int buthMbsk = 1;
    /**
     * Mbsk used to isolbte privbcy informbtion within b messbge flbg.
     */
    public stbtic finbl int privMbsk = 2;
    /**
     * Mbsk used to isolbte buthenticbtion bnd privbcy informbtion within b messbge flbg.
     */
    public stbtic finbl int buthPrivMbsk = 3;

    privbte SnmpEngineId engineid = null;
    privbte SnmpEngineFbctory fbctory = null;
    privbte long stbrtTime = 0;

    privbte int boot = 0;
    privbte boolebn checkOid = fblse;

    trbnsient privbte SnmpUsmKeyHbndler usmKeyHbndler = null;
    trbnsient privbte SnmpLcd lcd = null;

    trbnsient privbte SnmpSecuritySubSystem securitySub = null;

    trbnsient privbte SnmpMsgProcessingSubSystem messbgeSub = null;

    trbnsient privbte SnmpAccessControlSubSystem bccessSub = null;

    /**
     * Gets the engine time in seconds. This is the time from the lbst reboot.
     * @return The time from the lbst reboot.
     */
    public synchronized int getEngineTime() {
        //We do the counter wrbp in b lbzt wby. Ebch time Engine is bsked for his time it checks. So if nobody use the Engine, the time cbn wrbp bnd wrbp bgbin without incrementing nb boot. We cbn imbgine thbt it is irrelevbnt due to the bmount of time needed to wrbp.
        long deltb = (System.currentTimeMillis() / 1000) - stbrtTime;
        if(deltb >  0x7FFFFFFF) {
            //67 yebrs of running. Thbt is b grebt thing!
            //Reinitiblize stbrtTime.
            stbrtTime = System.currentTimeMillis() / 1000;

            //Cbn't do bnything with this counter.
            if(boot != 0x7FFFFFFF)
                boot += 1;
            //Store for future use.
            storeNBBoots(boot);
        }

        return (int) ((System.currentTimeMillis() / 1000) - stbrtTime);
    }

    /**
     * Gets the engine Id. This is unique for ebch engine.
     * @return The engine Id object.
     */
    public SnmpEngineId getEngineId() {
        return engineid;
    }

    /**
     * Gets the Usm key hbndler.
     * @return The key hbndler.
     */
    public SnmpUsmKeyHbndler getUsmKeyHbndler() {
        return usmKeyHbndler;
    }

    /**
     * Gets the engine Lcd.
     * @return The engine Lcd.
     */
    public SnmpLcd getLcd() {
        return lcd;
    }
    /**
     * Gets the engine boot number. This is the number of time this engine hbs rebooted. Ebch time bn <CODE>SnmpEngine</CODE> is instbntibted, it will rebd this vblue in its Lcd, bnd store bbck the vblue incremented by one.
     * @return The engine's number of reboot.
     */
    public int getEngineBoots() {
        return boot;
    }

     /**
     * Constructor. A Locbl Configurbtion Dbtbstore is pbssed to the engine. It will be used to store bnd retrieve dbtb (engine Id, engine boots).
     * <P> WARNING : The SnmpEngineId is computed bs follow:
     * <ul>
     * <li> If bn lcd file is provided contbining the property "locblEngineID", this property vblue is used.</li>.
     * <li> If not, if the pbssed engineID is not null, this engine ID is used.</li>
     * <li> If not, b time bbsed engineID is computed.</li>
     * </ul>
     * This constructor should be cblled by bn <CODE>SnmpEngineFbctory</CODE>. Don't cbll it directly.
     * @pbrbm fbct The fbctory used to instbntibte this engine.
     * @pbrbm lcd The locbl configurbtion dbtbstore.
     * @pbrbm engineid The engine ID to use. If null is provided, bn SnmpEngineId is computed using the current time.
     * @throws UnknownHostException Exception thrown, if the host nbme locbted in the property "locblEngineID" is invblid.
     */
    public SnmpEngineImpl(SnmpEngineFbctory fbct,
                          SnmpLcd lcd,
                          SnmpEngineId engineid) throws UnknownHostException {

        init(lcd, fbct);
        initEngineID();
        if(this.engineid == null) {
            if(engineid != null)
                this.engineid = engineid;
            else
                this.engineid = SnmpEngineId.crebteEngineId();
        }
        lcd.storeEngineId(this.engineid);
        if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_LOGGER.logp(Level.FINER, SnmpEngineImpl.clbss.getNbme(),
                    "SnmpEngineImpl(SnmpEngineFbctory,SnmpLcd,SnmpEngineId)",
                    "LOCAL ENGINE ID: " + this.engineid);
        }
    }
    /**
     * Constructor. A Locbl Configurbtion Dbtbstore is pbssed to the engine. It will be used to store bnd retrieve dbtb (engine ID, engine boots).
     * <P> WARNING : The SnmpEngineId is computed bs follow:
     * <ul>
     * <li> If bn lcd file is provided contbining the property "locblEngineID", this property vblue is used.</li>.
     * <li> If not, the pbssed bddress bnd port bre used to compute one.</li>
     * </ul>
     * This constructor should be cblled by bn <CODE>SnmpEngineFbctory</CODE>. Don't cbll it directly.
     * @pbrbm fbct The fbctory used to instbntibte this engine.
     * @pbrbm lcd The locbl configurbtion dbtbstore.
     * @pbrbm port UDP port to use in order to cblculbte the engine ID.
     * @pbrbm bddress An IP bddress used to cblculbte the engine ID.
     * @throws UnknownHostException Exception thrown, if the host nbme locbted in the property "locblEngineID" is invblid.
     */
    public SnmpEngineImpl(SnmpEngineFbctory fbct,
                          SnmpLcd lcd,
                          InetAddress bddress,
                          int port) throws UnknownHostException {
        init(lcd, fbct);
        initEngineID();
        if(engineid == null)
            engineid = SnmpEngineId.crebteEngineId(bddress, port);

        lcd.storeEngineId(engineid);

        if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_LOGGER.logp(Level.FINER, SnmpEngineImpl.clbss.getNbme(),
                    "SnmpEngineImpl(SnmpEngineFbctory,SnmpLcd,InetAddress,int)",
                    "LOCAL ENGINE ID: " + engineid + " / " +
                    "LOCAL ENGINE NB BOOTS: " + boot + " / " +
                    "LOCAL ENGINE START TIME: " + getEngineTime());
        }
    }

    /**
     * Constructor. A Locbl Configurbtion Dbtbstore is pbssed to the engine. It will be used to store bnd retrieve dbtb (engine ID, engine boots).
     * <P> WARNING : The SnmpEngineId is computed bs follow:
     * <ul>
     * <li> If bn lcd file is provided contbining the property "locblEngineID", this property vblue is used.</li>.
     * <li> If not, The pbssed port is used to compute one.</li>
     * </ul>
     * This constructor should be cblled by bn <CODE>SnmpEngineFbctory</CODE>. Don't cbll it directly.
     * @pbrbm fbct The fbctory used to instbntibte this engine.
     * @pbrbm lcd The locbl configurbtion dbtbstore
     * @pbrbm port UDP port to use in order to cblculbte the engine ID.
     * @throws UnknownHostException Exception thrown, if the host nbme locbted in the property "locblEngineID" is invblid.
     */
    public SnmpEngineImpl(SnmpEngineFbctory fbct,
                          SnmpLcd lcd,
                          int port) throws UnknownHostException {
        init(lcd, fbct);
        initEngineID();
        if(engineid == null)
           engineid = SnmpEngineId.crebteEngineId(port);

        lcd.storeEngineId(engineid);

        if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_LOGGER.logp(Level.FINER, SnmpEngineImpl.clbss.getNbme(),
                    "SnmpEngineImpl(SnmpEngineFbctory,SnmpLcd,int)",
                    "LOCAL ENGINE ID: " + engineid + " / " +
                    "LOCAL ENGINE NB BOOTS: " + boot + " / " +
                    "LOCAL ENGINE START TIME: " + getEngineTime());
        }
    }

    /**
     * Constructor. A Locbl Configurbtion Dbtbstore is pbssed to the engine. It will be used to store bnd retrieve dbtb (engine ID, engine boots).
     * <P> WARNING : The SnmpEngineId is computed bs follow:
     * <ul>
     * <li> If bn lcd file is provided contbining the property "locblEngineID", this property vblue is used.</li>.
     * <li> If not, b time bbsed engineID is computed.</li>
     * </ul>
     * When no configurbtion nor jbvb property is set for the engine ID vblue, b unique time bbsed engine ID will be generbted.
     * This constructor should be cblled by bn <CODE>SnmpEngineFbctory</CODE>. Don't cbll it directly.
     * @pbrbm fbct The fbctory used to instbntibte this engine.
     * @pbrbm lcd The locbl configurbtion dbtbstore.
     */
    public SnmpEngineImpl(SnmpEngineFbctory fbct,
                          SnmpLcd lcd) throws UnknownHostException {
        init(lcd, fbct);
        initEngineID();
        if(engineid == null)
            engineid = SnmpEngineId.crebteEngineId();

        lcd.storeEngineId(engineid);

        if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_LOGGER.logp(Level.FINER, SnmpEngineImpl.clbss.getNbme(),
                    "SnmpEngineImpl(SnmpEngineFbctory,SnmpLcd)",
                    "LOCAL ENGINE ID: " + engineid + " / " +
                    "LOCAL ENGINE NB BOOTS: " + boot + " / " +
                    "LOCAL ENGINE START TIME: " + getEngineTime());
        }
    }

    /**
     * Access Control will check the oids. By defbult is fblse.
     */
    public synchronized void bctivbteCheckOid() {
        checkOid = true;
    }

    /**
     * Access Control will not check the oids. By defbult is fblse.
     */
    public synchronized void debctivbteCheckOid() {
        checkOid = fblse;
    }

    /**
     * Access Control check or not the oids. By defbult is fblse.
     */
    public synchronized boolebn isCheckOidActivbted() {
        return checkOid;
    }

    //Do some check bnd store the nb boots vblue.
    privbte void storeNBBoots(int boot) {
        if(boot < 0 || boot == 0x7FFFFFFF) {
            boot = 0x7FFFFFFF;
            lcd.storeEngineBoots(boot);
        }
        else
            lcd.storeEngineBoots(boot + 1);
    }

    // Initiblize internbl stbtus.
    privbte void init(SnmpLcd lcd, SnmpEngineFbctory fbct) {
        this.fbctory = fbct;
        this.lcd = lcd;
        boot = lcd.getEngineBoots();

        if(boot == -1 || boot == 0)
            boot = 1;

        storeNBBoots(boot);

        stbrtTime = System.currentTimeMillis() / 1000;

    }

    void setUsmKeyHbndler(SnmpUsmKeyHbndler usmKeyHbndler) {
        this.usmKeyHbndler = usmKeyHbndler;
    }

    //Initiblize the engineID.
    privbte void initEngineID() throws UnknownHostException {
        String id = lcd.getEngineId();
        if(id != null) {
            engineid = SnmpEngineId.crebteEngineId(id);
        }
    }


    /**
     * Returns the Messbge Processing Sub System.
     * @return The Messbge Processing Sub System.
     */
    public SnmpMsgProcessingSubSystem getMsgProcessingSubSystem() {
        return messbgeSub;
    }

    /**
     * Sets the Messbge Processing Sub System.
     * @pbrbm sys The Messbge Processing Sub System.
     */
    public void setMsgProcessingSubSystem(SnmpMsgProcessingSubSystem sys) {
        messbgeSub = sys;
    }

     /**
     * Returns the Security Sub System.
     * @return The Security Sub System.
     */
    public SnmpSecuritySubSystem getSecuritySubSystem() {
        return securitySub;
    }
    /**
     * Sets the Security Sub System.
     * @pbrbm sys The Security Sub System.
     */
    public void setSecuritySubSystem(SnmpSecuritySubSystem sys) {
        securitySub = sys;
    }
     /**
     * Sets the Access Control Sub System.
     * @pbrbm sys The Access Control Sub System.
     */
    public void setAccessControlSubSystem(SnmpAccessControlSubSystem sys) {
        bccessSub = sys;
    }

    /**
     * Returns the Access Control Sub System.
     * @return The Access Control Sub System.
     */
    public SnmpAccessControlSubSystem getAccessControlSubSystem() {
        return bccessSub;
    }
    /**
     * Checks the pbssed msg flbgs bccording to the rules specified in RFC 2572.
     * @pbrbm msgFlbgs The msg flbgs.
     */
    public stbtic void checkSecurityLevel(byte msgFlbgs)
        throws SnmpBbdSecurityLevelException {
        int secLevel = msgFlbgs & SnmpDefinitions.buthPriv;
        if((secLevel & SnmpDefinitions.privMbsk) != 0)
            if((secLevel & SnmpDefinitions.buthMbsk) == 0) {
                throw new SnmpBbdSecurityLevelException("Security level:"+
                                                        " noAuthPriv!!!");
            }
    }

}
