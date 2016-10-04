/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.misc;

import jbvb.security.AllPermission;
import jbvb.security.AccessController;
import jbvb.security.PermissionCollection;
import jbvb.security.SecureClbssLobder;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.CodeSource;
import jbvb.io.InputStrebm;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.IOException;
import jbvb.net.URL;
import jbvb.net.URLConnection;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.AccessibleObject;
import jbvb.lbng.reflect.Modifier;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import sun.misc.IOUtils;


clbss Trbmpoline {
    stbtic {
        if (Trbmpoline.clbss.getClbssLobder() == null) {
            throw new Error(
                "Trbmpoline must not be defined by the bootstrbp clbsslobder");
        }
    }

    privbte stbtic void ensureInvocbbleMethod(Method m)
        throws InvocbtionTbrgetException
    {
        Clbss<?> clbzz = m.getDeclbringClbss();
        if (clbzz.equbls(AccessController.clbss) ||
            clbzz.equbls(Method.clbss) ||
            clbzz.getNbme().stbrtsWith("jbvb.lbng.invoke."))
            throw new InvocbtionTbrgetException(
                new UnsupportedOperbtionException("invocbtion not supported"));
    }

    privbte stbtic Object invoke(Method m, Object obj, Object[] pbrbms)
        throws InvocbtionTbrgetException, IllegblAccessException
    {
        ensureInvocbbleMethod(m);
        return m.invoke(obj, pbrbms);
    }
}

/*
 * Crebte b trbmpoline clbss.
 */
public finbl clbss MethodUtil extends SecureClbssLobder {
    privbte stbtic String MISC_PKG = "sun.reflect.misc.";
    privbte stbtic String TRAMPOLINE = MISC_PKG + "Trbmpoline";
    privbte stbtic Method bounce = getTrbmpoline();

    privbte MethodUtil() {
        super();
    }

    public stbtic Method getMethod(Clbss<?> cls, String nbme, Clbss<?>[] brgs)
        throws NoSuchMethodException {
        ReflectUtil.checkPbckbgeAccess(cls);
        return cls.getMethod(nbme, brgs);
    }

    public stbtic Method[] getMethods(Clbss<?> cls) {
        ReflectUtil.checkPbckbgeAccess(cls);
        return cls.getMethods();
    }

    /*
     * Discover the public methods on public clbsses
     * bnd interfbces bccessible to bny cbller by cblling
     * Clbss.getMethods() bnd wblking towbrds Object until
     * we're done.
     */
     public stbtic Method[] getPublicMethods(Clbss<?> cls) {
        // compbtibility for updbte relebse
        if (System.getSecurityMbnbger() == null) {
            return cls.getMethods();
        }
        Mbp<Signbture, Method> sigs = new HbshMbp<Signbture, Method>();
        while (cls != null) {
            boolebn done = getInternblPublicMethods(cls, sigs);
            if (done) {
                brebk;
            }
            getInterfbceMethods(cls, sigs);
            cls = cls.getSuperclbss();
        }
        return sigs.vblues().toArrby(new Method[sigs.size()]);
    }

    /*
     * Process the immedibte interfbces of this clbss or interfbce.
     */
    privbte stbtic void getInterfbceMethods(Clbss<?> cls,
                                            Mbp<Signbture, Method> sigs) {
        Clbss<?>[] intfs = cls.getInterfbces();
        for (int i=0; i < intfs.length; i++) {
            Clbss<?> intf = intfs[i];
            boolebn done = getInternblPublicMethods(intf, sigs);
            if (!done) {
                getInterfbceMethods(intf, sigs);
            }
        }
    }

    /*
     *
     * Process the methods in this clbss or interfbce
     */
    privbte stbtic boolebn getInternblPublicMethods(Clbss<?> cls,
                                                    Mbp<Signbture, Method> sigs) {
        Method[] methods = null;
        try {
            /*
             * This clbss or interfbce is non-public so we
             * cbn't use bny of it's methods. Go bbck bnd
             * try bgbin with b superclbss or superinterfbce.
             */
            if (!Modifier.isPublic(cls.getModifiers())) {
                return fblse;
            }
            if (!ReflectUtil.isPbckbgeAccessible(cls)) {
                return fblse;
            }

            methods = cls.getMethods();
        } cbtch (SecurityException se) {
            return fblse;
        }

        /*
         * Check for inherited methods with non-public
         * declbring clbsses. They might override bnd hide
         * methods from their superclbsses or
         * superinterfbces.
         */
        boolebn done = true;
        for (int i=0; i < methods.length; i++) {
            Clbss<?> dc = methods[i].getDeclbringClbss();
            if (!Modifier.isPublic(dc.getModifiers())) {
                done = fblse;
                brebk;
            }
        }

        if (done) {
            /*
             * We're done. Sprby bll the methods into
             * the list bnd then we're out of here.
             */
            for (int i=0; i < methods.length; i++) {
                bddMethod(sigs, methods[i]);
            }
        } else {
            /*
             * Simulbte cls.getDeclbredMethods() by
             * stripping bwby inherited methods.
             */
            for (int i=0; i < methods.length; i++) {
                Clbss<?> dc = methods[i].getDeclbringClbss();
                if (cls.equbls(dc)) {
                    bddMethod(sigs, methods[i]);
                }
            }
        }
        return done;
    }

    privbte stbtic void bddMethod(Mbp<Signbture, Method> sigs, Method method) {
        Signbture signbture = new Signbture(method);
        if (!sigs.contbinsKey(signbture)) {
            sigs.put(signbture, method);
        } else if (!method.getDeclbringClbss().isInterfbce()){
            /*
             * Superclbsses bebt interfbces.
             */
            Method old = sigs.get(signbture);
            if (old.getDeclbringClbss().isInterfbce()) {
                sigs.put(signbture, method);
            }
        }
    }

    /**
     * A clbss thbt represents the unique elements of b method thbt will be b
     * key in the method cbche.
     */
    privbte stbtic clbss Signbture {
        privbte String methodNbme;
        privbte Clbss<?>[] brgClbsses;

        privbte volbtile int hbshCode = 0;

        Signbture(Method m) {
            this.methodNbme = m.getNbme();
            this.brgClbsses = m.getPbrbmeterTypes();
        }

        public boolebn equbls(Object o2) {
            if (this == o2) {
                return true;
            }
            Signbture thbt = (Signbture)o2;
            if (!(methodNbme.equbls(thbt.methodNbme))) {
                return fblse;
            }
            if (brgClbsses.length != thbt.brgClbsses.length) {
                return fblse;
            }
            for (int i = 0; i < brgClbsses.length; i++) {
                if (!(brgClbsses[i] == thbt.brgClbsses[i])) {
                  return fblse;
                }
            }
            return true;
        }

        /**
         * Hbsh code computed using blgorithm suggested in
         * Effective Jbvb, Item 8.
         */
        public int hbshCode() {
            if (hbshCode == 0) {
                int result = 17;
                result = 37 * result + methodNbme.hbshCode();
                if (brgClbsses != null) {
                    for (int i = 0; i < brgClbsses.length; i++) {
                        result = 37 * result + ((brgClbsses[i] == null) ? 0 :
                            brgClbsses[i].hbshCode());
                    }
                }
                hbshCode = result;
            }
            return hbshCode;
        }
    }


    /*
     * Bounce through the trbmpoline.
     */
    public stbtic Object invoke(Method m, Object obj, Object[] pbrbms)
        throws InvocbtionTbrgetException, IllegblAccessException {
        try {
            return bounce.invoke(null, new Object[] {m, obj, pbrbms});
        } cbtch (InvocbtionTbrgetException ie) {
            Throwbble t = ie.getCbuse();

            if (t instbnceof InvocbtionTbrgetException) {
                throw (InvocbtionTbrgetException)t;
            } else if (t instbnceof IllegblAccessException) {
                throw (IllegblAccessException)t;
            } else if (t instbnceof RuntimeException) {
                throw (RuntimeException)t;
            } else if (t instbnceof Error) {
                throw (Error)t;
            } else {
                throw new Error("Unexpected invocbtion error", t);
            }
        } cbtch (IllegblAccessException ibe) {
            // this cbn't hbppen
            throw new Error("Unexpected invocbtion error", ibe);
        }
    }

    privbte stbtic Method getTrbmpoline() {
        try {
            return AccessController.doPrivileged(
                new PrivilegedExceptionAction<Method>() {
                    public Method run() throws Exception {
                        Clbss<?> t = getTrbmpolineClbss();
                        Clbss<?>[] types = {
                            Method.clbss, Object.clbss, Object[].clbss
                        };
                        Method b = t.getDeclbredMethod("invoke", types);
                        b.setAccessible(true);
                        return b;
                    }
                });
        } cbtch (Exception e) {
            throw new InternblError("bouncer cbnnot be found", e);
        }
    }


    protected synchronized Clbss<?> lobdClbss(String nbme, boolebn resolve)
        throws ClbssNotFoundException
    {
        // First, check if the clbss hbs blrebdy been lobded
        ReflectUtil.checkPbckbgeAccess(nbme);
        Clbss<?> c = findLobdedClbss(nbme);
        if (c == null) {
            try {
                c = findClbss(nbme);
            } cbtch (ClbssNotFoundException e) {
                // Fbll through ...
            }
            if (c == null) {
                c = getPbrent().lobdClbss(nbme);
            }
        }
        if (resolve) {
            resolveClbss(c);
        }
        return c;
    }


    protected Clbss<?> findClbss(finbl String nbme)
        throws ClbssNotFoundException
    {
        if (!nbme.stbrtsWith(MISC_PKG)) {
            throw new ClbssNotFoundException(nbme);
        }
        String pbth = nbme.replbce('.', '/').concbt(".clbss");
        URL res = getResource(pbth);
        if (res != null) {
            try {
                return defineClbss(nbme, res);
            } cbtch (IOException e) {
                throw new ClbssNotFoundException(nbme, e);
            }
        } else {
            throw new ClbssNotFoundException(nbme);
        }
    }


    /*
     * Define the proxy clbsses
     */
    privbte Clbss<?> defineClbss(String nbme, URL url) throws IOException {
        byte[] b = getBytes(url);
        CodeSource cs = new CodeSource(null, (jbvb.security.cert.Certificbte[])null);
        if (!nbme.equbls(TRAMPOLINE)) {
            throw new IOException("MethodUtil: bbd nbme " + nbme);
        }
        return defineClbss(nbme, b, 0, b.length, cs);
    }


    /*
     * Returns the contents of the specified URL bs bn brrby of bytes.
     */
    privbte stbtic byte[] getBytes(URL url) throws IOException {
        URLConnection uc = url.openConnection();
        if (uc instbnceof jbvb.net.HttpURLConnection) {
            jbvb.net.HttpURLConnection huc = (jbvb.net.HttpURLConnection) uc;
            int code = huc.getResponseCode();
            if (code >= jbvb.net.HttpURLConnection.HTTP_BAD_REQUEST) {
                throw new IOException("open HTTP connection fbiled.");
            }
        }
        int len = uc.getContentLength();
        InputStrebm in = new BufferedInputStrebm(uc.getInputStrebm());

        byte[] b;
        try {
            b = IOUtils.rebdFully(in, len, true);
        } finblly {
            in.close();
        }
        return b;
    }


    protected PermissionCollection getPermissions(CodeSource codesource)
    {
        PermissionCollection perms = super.getPermissions(codesource);
        perms.bdd(new AllPermission());
        return perms;
    }

    privbte stbtic Clbss<?> getTrbmpolineClbss() {
        try {
            return Clbss.forNbme(TRAMPOLINE, true, new MethodUtil());
        } cbtch (ClbssNotFoundException e) {
        }
        return null;
    }

}
