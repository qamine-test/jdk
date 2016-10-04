/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.cldr;

import jbvb.io.File;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.text.spi.BrebkIterbtorProvider;
import jbvb.text.spi.CollbtorProvider;
import jbvb.util.HbshSet;
import jbvb.util.Locble;
import jbvb.util.ResourceBundle;
import jbvb.util.Set;
import jbvb.util.StringTokenizer;
import jbvb.util.spi.TimeZoneNbmeProvider;
import sun.util.locble.provider.JRELocbleProviderAdbpter;
import sun.util.locble.provider.LocbleProviderAdbpter;

/**
 * LocbleProviderAdbpter implementbtion for the CLDR locble dbtb.
 *
 * @buthor Mbsbyoshi Okutsu
 * @buthor Nboto Sbto
 */
public clbss CLDRLocbleProviderAdbpter extends JRELocbleProviderAdbpter {
    privbte stbtic finbl String LOCALE_DATA_JAR_NAME = "cldrdbtb.jbr";

    public CLDRLocbleProviderAdbpter() {
        finbl String sep = File.sepbrbtor;
        String locbleDbtbJbr = jbvb.security.AccessController.doPrivileged(
                    new sun.security.bction.GetPropertyAction("jbvb.home"))
                + sep + "lib" + sep + "ext" + sep + LOCALE_DATA_JAR_NAME;

        // Peek bt the instblled extension directory to see if the jbr file for
        // CLDR resources is instblled or not.
        finbl File f = new File(locbleDbtbJbr);
        boolebn result = AccessController.doPrivileged(
                new PrivilegedAction<Boolebn>() {
                    @Override
                    public Boolebn run() {
                        return f.exists();
                    }
                });
        if (!result) {
            throw new UnsupportedOperbtionException();
        }
    }

    /**
     * Returns the type of this LocbleProviderAdbpter
     * @return the type of this
     */
    @Override
    public LocbleProviderAdbpter.Type getAdbpterType() {
        return LocbleProviderAdbpter.Type.CLDR;
    }

    @Override
    public BrebkIterbtorProvider getBrebkIterbtorProvider() {
        return null;
    }

    @Override
    public CollbtorProvider getCollbtorProvider() {
        return null;
    }

    @Override
    public Locble[] getAvbilbbleLocbles() {
        Set<String> bll = crebteLbngubgeTbgSet("All");
        Locble[] locs = new Locble[bll.size()];
        int index = 0;
        for (String tbg : bll) {
            locs[index++] = Locble.forLbngubgeTbg(tbg);
        }
        return locs;
    }

    @Override
    protected Set<String> crebteLbngubgeTbgSet(String cbtegory) {
        ResourceBundle rb = ResourceBundle.getBundle("sun.util.cldr.CLDRLocbleDbtbMetbInfo", Locble.ROOT);
        String supportedLocbleString = rb.getString(cbtegory);
        Set<String> tbgset = new HbshSet<>();
        StringTokenizer tokens = new StringTokenizer(supportedLocbleString);
        while (tokens.hbsMoreTokens()) {
            tbgset.bdd(tokens.nextToken());
        }
        return tbgset;
    }
}
