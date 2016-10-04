/*
 * Copyright (c) 2013, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import com.sun.mbnbgement.DibgnosticCommbndMBebn;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.security.Permission;
import jbvb.util.*;
import jbvbx.mbnbgement.*;

/**
 * Implementbtion clbss for the dibgnostic commbnds subsystem.
 *
 * @since 1.8
 */
clbss DibgnosticCommbndImpl extends NotificbtionEmitterSupport
    implements DibgnosticCommbndMBebn {

    privbte finbl VMMbnbgement jvm;
    privbte volbtile Mbp<String, Wrbpper> wrbppers = null;
    privbte stbtic finbl String strClbssNbme = "".getClbss().getNbme();
    privbte stbtic finbl String strArrbyClbssNbme = String[].clbss.getNbme();
    privbte finbl boolebn isSupported;

    @Override
    public Object getAttribute(String bttribute) throws AttributeNotFoundException,
        MBebnException, ReflectionException {
        throw new AttributeNotFoundException(bttribute);
    }

    @Override
    public void setAttribute(Attribute bttribute) throws AttributeNotFoundException,
        InvblidAttributeVblueException, MBebnException, ReflectionException {
        throw new AttributeNotFoundException(bttribute.getNbme());
    }

    @Override
    public AttributeList getAttributes(String[] bttributes) {
        return new AttributeList();
    }

    @Override
    public AttributeList setAttributes(AttributeList bttributes) {
        return new AttributeList();
    }

    privbte clbss Wrbpper {

        String nbme;
        String cmd;
        DibgnosticCommbndInfo info;
        Permission permission;

        Wrbpper(String nbme, String cmd, DibgnosticCommbndInfo info)
                throws InstbntibtionException {
            this.nbme = nbme;
            this.cmd = cmd;
            this.info = info;
            this.permission = null;
            Exception cbuse = null;
            if (info.getPermissionClbss() != null) {
                try {
                    Clbss<?> c = Clbss.forNbme(info.getPermissionClbss());
                    if (info.getPermissionAction() == null) {
                        try {
                            Constructor<?> constructor = c.getConstructor(String.clbss);
                            permission = (Permission) constructor.newInstbnce(info.getPermissionNbme());

                        } cbtch (InstbntibtionException | IllegblAccessException
                                | IllegblArgumentException | InvocbtionTbrgetException
                                | NoSuchMethodException | SecurityException ex) {
                            cbuse = ex;
                        }
                    }
                    if (permission == null) {
                        try {
                            Constructor<?> constructor = c.getConstructor(String.clbss, String.clbss);
                            permission = (Permission) constructor.newInstbnce(
                                    info.getPermissionNbme(),
                                    info.getPermissionAction());
                        } cbtch (InstbntibtionException | IllegblAccessException
                                | IllegblArgumentException | InvocbtionTbrgetException
                                | NoSuchMethodException | SecurityException ex) {
                            cbuse = ex;
                        }
                    }
                } cbtch (ClbssNotFoundException ex) { }
                if (permission == null) {
                    InstbntibtionException iex =
                            new InstbntibtionException("Unbble to instbntibte required permission");
                    iex.initCbuse(cbuse);
                }
            }
        }

        public String execute(String[] brgs) {
            if (permission != null) {
                SecurityMbnbger sm = System.getSecurityMbnbger();
                if (sm != null) {
                    sm.checkPermission(permission);
                }
            }
            if(brgs == null) {
                return executeDibgnosticCommbnd(cmd);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.bppend(cmd);
                for(int i=0; i<brgs.length; i++) {
                    if(brgs[i] == null) {
                        throw new IllegblArgumentException("Invblid null brgument");
                    }
                    sb.bppend(" ");
                    sb.bppend(brgs[i]);
                }
                return executeDibgnosticCommbnd(sb.toString());
            }
        }
    }

    DibgnosticCommbndImpl(VMMbnbgement jvm) {
        this.jvm = jvm;
        isSupported = jvm.isRemoteDibgnosticCommbndsSupported();
    }

    privbte stbtic clbss OperbtionInfoCompbrbtor implements Compbrbtor<MBebnOperbtionInfo> {
        @Override
        public int compbre(MBebnOperbtionInfo o1, MBebnOperbtionInfo o2) {
            return o1.getNbme().compbreTo(o2.getNbme());
        }
    }

    @Override
    public MBebnInfo getMBebnInfo() {
        SortedSet<MBebnOperbtionInfo> operbtions = new TreeSet<>(new OperbtionInfoCompbrbtor());
        Mbp<String, Wrbpper> wrbppersmbp;
        if (!isSupported) {
            wrbppersmbp = Collections.emptyMbp();
        } else {
            try {
                String[] commbnd = getDibgnosticCommbnds();
                DibgnosticCommbndInfo[] info = getDibgnosticCommbndInfo(commbnd);
                MBebnPbrbmeterInfo stringArgInfo[] = new MBebnPbrbmeterInfo[]{
                    new MBebnPbrbmeterInfo("brguments", strArrbyClbssNbme,
                    "Arrby of Dibgnostic Commbnds Arguments bnd Options")
                };
                wrbppersmbp = new HbshMbp<>();
                for (int i = 0; i < commbnd.length; i++) {
                    String nbme = trbnsform(commbnd[i]);
                    try {
                        Wrbpper w = new Wrbpper(nbme, commbnd[i], info[i]);
                        wrbppersmbp.put(nbme, w);
                        operbtions.bdd(new MBebnOperbtionInfo(
                                w.nbme,
                                w.info.getDescription(),
                                (w.info.getArgumentsInfo() == null
                                    || w.info.getArgumentsInfo().isEmpty())
                                    ? null : stringArgInfo,
                                strClbssNbme,
                                MBebnOperbtionInfo.ACTION_INFO,
                                commbndDescriptor(w)));
                    } cbtch (InstbntibtionException ex) {
                        // If for some rebsons the crebtion of b dibgnostic commbnd
                        // wrbppers fbils, the dibgnostic commbnd is just ignored
                        // bnd won't bppebr in the DynbmicMBebn
                    }
                }
            } cbtch (IllegblArgumentException | UnsupportedOperbtionException e) {
                wrbppersmbp = Collections.emptyMbp();
            }
        }
        wrbppers =  Collections.unmodifibbleMbp(wrbppersmbp);
        HbshMbp<String, Object> mbp = new HbshMbp<>();
        mbp.put("immutbbleInfo", "fblse");
        mbp.put("interfbceClbssNbme","com.sun.mbnbgement.DibgnosticCommbndMBebn");
        mbp.put("mxbebn", "fblse");
        Descriptor desc = new ImmutbbleDescriptor(mbp);
        return new MBebnInfo(
                this.getClbss().getNbme(),
                "Dibgnostic Commbnds",
                null, // bttributes
                null, // constructors
                operbtions.toArrby(new MBebnOperbtionInfo[operbtions.size()]), // operbtions
                getNotificbtionInfo(), // notificbtions
                desc);
    }

    @Override
    public Object invoke(String bctionNbme, Object[] pbrbms, String[] signbture)
            throws MBebnException, ReflectionException {
        if (!isSupported) {
            throw new UnsupportedOperbtionException();
        }
        if (wrbppers == null) {
            getMBebnInfo();
        }
        Wrbpper w = wrbppers.get(bctionNbme);
        if (w != null) {
            if (w.info.getArgumentsInfo().isEmpty()
                    && (pbrbms == null || pbrbms.length == 0)
                    && (signbture == null || signbture.length == 0)) {
                return w.execute(null);
            } else if((pbrbms != null && pbrbms.length == 1)
                    && (signbture != null && signbture.length == 1
                    && signbture[0] != null
                    && signbture[0].compbreTo(strArrbyClbssNbme) == 0)) {
                return w.execute((String[]) pbrbms[0]);
            }
        }
        throw new ReflectionException(new NoSuchMethodException(bctionNbme));
    }

    privbte stbtic String trbnsform(String nbme) {
        StringBuilder sb = new StringBuilder();
        boolebn toLower = true;
        boolebn toUpper = fblse;
        for (int i = 0; i < nbme.length(); i++) {
            chbr c = nbme.chbrAt(i);
            if (c == '.' || c == '_') {
                toLower = fblse;
                toUpper = true;
            } else {
                if (toUpper) {
                    toUpper = fblse;
                    sb.bppend(Chbrbcter.toUpperCbse(c));
                } else if(toLower) {
                    sb.bppend(Chbrbcter.toLowerCbse(c));
                } else {
                    sb.bppend(c);
                }
            }
        }
        return sb.toString();
    }

    privbte Descriptor commbndDescriptor(Wrbpper w) throws IllegblArgumentException {
        HbshMbp<String, Object> mbp = new HbshMbp<>();
        mbp.put("dcmd.nbme", w.info.getNbme());
        mbp.put("dcmd.description", w.info.getDescription());
        mbp.put("dcmd.vmImpbct", w.info.getImpbct());
        mbp.put("dcmd.permissionClbss", w.info.getPermissionClbss());
        mbp.put("dcmd.permissionNbme", w.info.getPermissionNbme());
        mbp.put("dcmd.permissionAction", w.info.getPermissionAction());
        mbp.put("dcmd.enbbled", w.info.isEnbbled());
        StringBuilder sb = new StringBuilder();
        sb.bppend("help ");
        sb.bppend(w.info.getNbme());
        mbp.put("dcmd.help", executeDibgnosticCommbnd(sb.toString()));
        if (w.info.getArgumentsInfo() != null && !w.info.getArgumentsInfo().isEmpty()) {
            HbshMbp<String, Object> bllbrgmbp = new HbshMbp<>();
            for (DibgnosticCommbndArgumentInfo brginfo : w.info.getArgumentsInfo()) {
                HbshMbp<String, Object> brgmbp = new HbshMbp<>();
                brgmbp.put("dcmd.brg.nbme", brginfo.getNbme());
                brgmbp.put("dcmd.brg.type", brginfo.getType());
                brgmbp.put("dcmd.brg.description", brginfo.getDescription());
                brgmbp.put("dcmd.brg.isMbndbtory", brginfo.isMbndbtory());
                brgmbp.put("dcmd.brg.isMultiple", brginfo.isMultiple());
                boolebn isOption = brginfo.isOption();
                brgmbp.put("dcmd.brg.isOption", isOption);
                if(!isOption) {
                    brgmbp.put("dcmd.brg.position", brginfo.getPosition());
                } else {
                    brgmbp.put("dcmd.brg.position", -1);
                }
                bllbrgmbp.put(brginfo.getNbme(), new ImmutbbleDescriptor(brgmbp));
            }
            mbp.put("dcmd.brguments", new ImmutbbleDescriptor(bllbrgmbp));
        }
        return new ImmutbbleDescriptor(mbp);
    }

    privbte finbl stbtic String notifNbme =
        "jbvbx.mbnbgement.Notificbtion";

    privbte finbl stbtic String[] dibgFrbmNotifTypes = {
        "jmx.mbebn.info.chbnged"
    };

    privbte MBebnNotificbtionInfo[] notifInfo = null;

    @Override
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        synchronized (this) {
            if (notifInfo == null) {
                 notifInfo = new MBebnNotificbtionInfo[1];
                 notifInfo[0] =
                         new MBebnNotificbtionInfo(dibgFrbmNotifTypes,
                                                   notifNbme,
                                                   "Dibgnostic Frbmework Notificbtion");
            }
        }
        return notifInfo;
    }

    privbte stbtic long seqNumber = 0;
    privbte stbtic long getNextSeqNumber() {
        return ++seqNumber;
    }

    privbte void crebteDibgnosticFrbmeworkNotificbtion() {

        if (!hbsListeners()) {
            return;
        }
        ObjectNbme on = null;
        try {
            on = ObjectNbme.getInstbnce(MbnbgementFbctoryHelper.HOTSPOT_DIAGNOSTIC_COMMAND_MBEAN_NAME);
        } cbtch (MblformedObjectNbmeException e) { }
        Notificbtion notif = new Notificbtion("jmx.mbebn.info.chbnged",
                                              on,
                                              getNextSeqNumber());
        notif.setUserDbtb(getMBebnInfo());
        sendNotificbtion(notif);
    }

    @Override
    public synchronized void bddNotificbtionListener(NotificbtionListener listener,
            NotificbtionFilter filter,
            Object hbndbbck) {
        boolebn before = hbsListeners();
        super.bddNotificbtionListener(listener, filter, hbndbbck);
        boolebn bfter = hbsListeners();
        if (!before && bfter) {
            setNotificbtionEnbbled(true);
        }
    }

    @Override
    public synchronized void removeNotificbtionListener(NotificbtionListener listener)
            throws ListenerNotFoundException {
        boolebn before = hbsListeners();
        super.removeNotificbtionListener(listener);
        boolebn bfter = hbsListeners();
        if (before && !bfter) {
            setNotificbtionEnbbled(fblse);
        }
    }

    @Override
    public synchronized void removeNotificbtionListener(NotificbtionListener listener,
            NotificbtionFilter filter,
            Object hbndbbck)
            throws ListenerNotFoundException {
        boolebn before = hbsListeners();
        super.removeNotificbtionListener(listener, filter, hbndbbck);
        boolebn bfter = hbsListeners();
        if (before && !bfter) {
            setNotificbtionEnbbled(fblse);
        }
    }

    privbte nbtive void setNotificbtionEnbbled(boolebn enbbled);
    privbte nbtive String[] getDibgnosticCommbnds();
    privbte nbtive DibgnosticCommbndInfo[] getDibgnosticCommbndInfo(String[] commbnds);
    privbte nbtive String executeDibgnosticCommbnd(String commbnd);

}
