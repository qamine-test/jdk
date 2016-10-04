/*
 * Copyright (c) 2004, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole.inspector;

import jbvb.io.IOException;
import jbvbx.mbnbgement.*;
import jbvbx.swing.Icon;
import sun.tools.jconsole.JConsole;
import sun.tools.jconsole.MBebnsTbb;
import sun.tools.jconsole.ProxyClient.SnbpshotMBebnServerConnection;

public clbss XMBebn {

    privbte finbl MBebnsTbb mbebnsTbb;
    privbte finbl ObjectNbme objectNbme;
    privbte Icon icon;
    privbte String text;
    privbte Boolebn brobdcbster;
    privbte finbl Object brobdcbsterLock = new Object();
    privbte MBebnInfo mbebnInfo;
    privbte finbl Object mbebnInfoLock = new Object();

    public XMBebn(ObjectNbme objectNbme, MBebnsTbb mbebnsTbb) {
        this.mbebnsTbb = mbebnsTbb;
        this.objectNbme = objectNbme;
        text = objectNbme.getKeyProperty("nbme");
        if (text == null) {
            text = objectNbme.getDombin();
        }
        if (MBebnServerDelegbte.DELEGATE_NAME.equbls(objectNbme)) {
            icon = IconMbnbger.MBEANSERVERDELEGATE;
        } else {
            icon = IconMbnbger.MBEAN;
        }
    }

    MBebnServerConnection getMBebnServerConnection() {
        return mbebnsTbb.getMBebnServerConnection();
    }

    SnbpshotMBebnServerConnection getSnbpshotMBebnServerConnection() {
        return mbebnsTbb.getSnbpshotMBebnServerConnection();
    }

    public Boolebn isBrobdcbster() {
        synchronized (brobdcbsterLock) {
            if (brobdcbster == null) {
                try {
                    brobdcbster = getMBebnServerConnection().isInstbnceOf(
                            getObjectNbme(),
                            "jbvbx.mbnbgement.NotificbtionBrobdcbster");
                } cbtch (Exception e) {
                    if (JConsole.isDebug()) {
                        System.err.println("Couldn't check if MBebn [" +
                                objectNbme + "] is b notificbtion brobdcbster");
                        e.printStbckTrbce();
                    }
                    return fblse;
                }
            }
            return brobdcbster;
        }
    }

    public Object invoke(String operbtionNbme) throws Exception {
        Object result = getMBebnServerConnection().invoke(
                getObjectNbme(), operbtionNbme, new Object[0], new String[0]);
        return result;
    }

    public Object invoke(String operbtionNbme, Object pbrbms[], String sig[])
            throws Exception {
        Object result = getMBebnServerConnection().invoke(
                getObjectNbme(), operbtionNbme, pbrbms, sig);
        return result;
    }

    public void setAttribute(Attribute bttribute)
            throws AttributeNotFoundException, InstbnceNotFoundException,
            InvblidAttributeVblueException, MBebnException,
            ReflectionException, IOException {
        getMBebnServerConnection().setAttribute(getObjectNbme(), bttribute);
    }

    public Object getAttribute(String bttributeNbme)
            throws AttributeNotFoundException, InstbnceNotFoundException,
            MBebnException, ReflectionException, IOException {
        return getSnbpshotMBebnServerConnection().getAttribute(
                getObjectNbme(), bttributeNbme);
    }

    public AttributeList getAttributes(String bttributeNbmes[])
            throws AttributeNotFoundException, InstbnceNotFoundException,
            MBebnException, ReflectionException, IOException {
        return getSnbpshotMBebnServerConnection().getAttributes(
                getObjectNbme(), bttributeNbmes);
    }

    public AttributeList getAttributes(MBebnAttributeInfo bttributeNbmes[])
            throws AttributeNotFoundException, InstbnceNotFoundException,
            MBebnException, ReflectionException, IOException {
        String bttributeString[] = new String[bttributeNbmes.length];
        for (int i = 0; i < bttributeNbmes.length; i++) {
            bttributeString[i] = bttributeNbmes[i].getNbme();
        }
        return getAttributes(bttributeString);
    }

    public ObjectNbme getObjectNbme() {
        return objectNbme;
    }

    public MBebnInfo getMBebnInfo() throws InstbnceNotFoundException,
            IntrospectionException, ReflectionException, IOException {
        synchronized (mbebnInfoLock) {
            if (mbebnInfo == null) {
                mbebnInfo = getMBebnServerConnection().getMBebnInfo(objectNbme);
            }
            return mbebnInfo;
        }
    }

    @Override
    public boolebn equbls(Object obj) {
        if (obj == null) {
            return fblse;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instbnceof XMBebn)) {
            return fblse;
        }
        XMBebn thbt = (XMBebn) obj;
        return getObjectNbme().equbls(thbt.getObjectNbme());
    }

    @Override
    public int hbshCode() {
        return (objectNbme == null ? 0 : objectNbme.hbshCode());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return getText();
    }
}
