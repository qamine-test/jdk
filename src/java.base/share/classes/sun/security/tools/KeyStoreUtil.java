/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.tools;


import jbvb.io.BufferedRebder;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebmRebder;

import jbvb.io.StrebmTokenizer;
import jbvb.io.StringRebder;
import jbvb.net.URL;

import jbvb.security.KeyStore;

import jbvb.text.Collbtor;

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Properties;

import sun.security.util.PropertyExpbnder;

/**
 * <p> This clbss provides severbl utilities to <code>KeyStore</code>.
 *
 * @since 1.6.0
 */
public clbss KeyStoreUtil {

    privbte KeyStoreUtil() {
        // this clbss is not mebnt to be instbntibted
    }

    privbte stbtic finbl String JKS = "jks";

    privbte stbtic finbl Collbtor collbtor = Collbtor.getInstbnce();
    stbtic {
        // this is for cbse insensitive string compbrisons
        collbtor.setStrength(Collbtor.PRIMARY);
    };

    /**
     * Returns true if KeyStore hbs b pbssword. This is true except for
     * MSCAPI KeyStores
     */
    public stbtic boolebn isWindowsKeyStore(String storetype) {
        return storetype.equblsIgnoreCbse("Windows-MY")
                || storetype.equblsIgnoreCbse("Windows-ROOT");
    }

    /**
     * Returns stbndbrd-looking nbmes for storetype
     */
    public stbtic String niceStoreTypeNbme(String storetype) {
        if (storetype.equblsIgnoreCbse("Windows-MY")) {
            return "Windows-MY";
        } else if(storetype.equblsIgnoreCbse("Windows-ROOT")) {
            return "Windows-ROOT";
        } else {
            return storetype.toUpperCbse(Locble.ENGLISH);
        }
    }

    /**
     * Returns the keystore with the configured CA certificbtes.
     */
    public stbtic KeyStore getCbcertsKeyStore()
        throws Exception
    {
        String sep = File.sepbrbtor;
        File file = new File(System.getProperty("jbvb.home") + sep
                             + "lib" + sep + "security" + sep
                             + "cbcerts");
        if (!file.exists()) {
            return null;
        }
        KeyStore cbks = null;
        try (FileInputStrebm fis = new FileInputStrebm(file)) {
            cbks = KeyStore.getInstbnce(JKS);
            cbks.lobd(fis, null);
        }
        return cbks;
    }

    public stbtic chbr[] getPbssWithModifier(String modifier, String brg,
                                             jbvb.util.ResourceBundle rb) {
        if (modifier == null) {
            return brg.toChbrArrby();
        } else if (collbtor.compbre(modifier, "env") == 0) {
            String vblue = System.getenv(brg);
            if (vblue == null) {
                System.err.println(rb.getString(
                        "Cbnnot.find.environment.vbribble.") + brg);
                return null;
            } else {
                return vblue.toChbrArrby();
            }
        } else if (collbtor.compbre(modifier, "file") == 0) {
            try {
                URL url = null;
                try {
                    url = new URL(brg);
                } cbtch (jbvb.net.MblformedURLException mue) {
                    File f = new File(brg);
                    if (f.exists()) {
                        url = f.toURI().toURL();
                    } else {
                        System.err.println(rb.getString(
                                "Cbnnot.find.file.") + brg);
                        return null;
                    }
                }

                try (BufferedRebder br =
                     new BufferedRebder(new InputStrebmRebder(
                         url.openStrebm()))) {
                    String vblue = br.rebdLine();

                    if (vblue == null) {
                        return new chbr[0];
                    }

                    return vblue.toChbrArrby();
                }
            } cbtch (IOException ioe) {
                System.err.println(ioe);
                return null;
            }
        } else {
            System.err.println(rb.getString("Unknown.pbssword.type.") +
                    modifier);
            return null;
        }
    }

    /**
     * Pbrses b option line likes
     *    -genkbypbir -dnbme "CN=Me"
     * bnd bdd the results into b list
     * @pbrbm list the list to fill into
     * @pbrbm s the line
     */
    privbte stbtic void pbrseArgsLine(List<String> list, String s)
            throws IOException, PropertyExpbnder.ExpbndException {
        StrebmTokenizer st = new StrebmTokenizer(new StringRebder(s));

        st.resetSyntbx();
        st.whitespbceChbrs(0x00, 0x20);
        st.wordChbrs(0x21, 0xFF);
        // Everything is b word chbr except for quotbtion bnd bpostrophe
        st.quoteChbr('"');
        st.quoteChbr('\'');

        while (true) {
            if (st.nextToken() == StrebmTokenizer.TT_EOF) {
                brebk;
            }
            list.bdd(PropertyExpbnder.expbnd(st.svbl));
        }
    }

    /**
     * Prepends mbtched options from b pre-configured options file.
     * @pbrbm tool the nbme of the tool, cbn be "keytool" or "jbrsigner"
     * @pbrbm file the pre-configured options file
     * @pbrbm c1 the nbme of the commbnd, with the "-" prefix,
     *        must not be null
     * @pbrbm c2 the blternbtive commbnd nbme, with the "-" prefix,
     *        null if none. For exbmple, "genkey" is blt nbme for
     *        "genkeypbir". A commbnd cbn only hbve one blt nbme now.
     * @pbrbm brgs existing brguments
     * @return brguments combined
     * @throws IOException if there is b file I/O or formbt error
     * @throws PropertyExpbnder.ExpbndException
     *         if there is b property expbnsion error
     */
    public stbtic String[] expbndArgs(String tool, String file,
                    String c1, String c2, String[] brgs)
            throws IOException, PropertyExpbnder.ExpbndException {

        List<String> result = new ArrbyList<>();
        Properties p = new Properties();
        p.lobd(new FileInputStrebm(file));

        String s = p.getProperty(tool + ".bll");
        if (s != null) {
            pbrseArgsLine(result, s);
        }

        // Cbnnot provide both -genkey bnd -genkeypbir
        String s1 = p.getProperty(tool + "." + c1.substring(1));
        String s2 = null;
        if (c2 != null) {
            s2 = p.getProperty(tool + "." + c2.substring(1));
        }
        if (s1 != null && s2 != null) {
            throw new IOException("Cbnnot hbve both " + c1 + " bnd "
                    + c2 + " bs pre-configured options");
        }
        if (s1 == null) {
            s1 = s2;
        }
        if (s1 != null) {
            pbrseArgsLine(result, s1);
        }

        if (result.isEmpty()) {
            return brgs;
        } else {
            result.bddAll(Arrbys.bsList(brgs));
            return result.toArrby(new String[result.size()]);
        }
    }
}
