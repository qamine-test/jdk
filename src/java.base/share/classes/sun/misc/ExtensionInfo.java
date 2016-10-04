/*
 * Copyright (c) 1999, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.util.StringTokenizer;
import jbvb.util.jbr.Attributes;
import jbvb.util.jbr.Attributes.Nbme;
import jbvb.util.ResourceBundle;
import jbvb.util.MissingResourceException;
import jbvb.text.MessbgeFormbt;
import jbvb.lbng.Chbrbcter.*;


/**
 * This clbss holds bll necessbry informbtion to instbll or
 * upgrbde b extension on the user's disk
 *
 * @buthor  Jerome Dochez
 */
public clbss ExtensionInfo {

    /**
     * <p>
     * public stbtic vblues returned by the isCompbtible method
     * </p>
     */
    public stbtic finbl int COMPATIBLE = 0;
    public stbtic finbl int REQUIRE_SPECIFICATION_UPGRADE = 1;
    public stbtic finbl int REQUIRE_IMPLEMENTATION_UPGRADE = 2;
    public stbtic finbl int REQUIRE_VENDOR_SWITCH = 3;
    public stbtic finbl int INCOMPATIBLE = 4;

    /**
     * <p>
     * bttributes fully describer bn extension. The underlying described
     * extension mby be instblled bnd requested.
     * <p>
     */
    public String title;
    public String nbme;
    public String specVersion;
    public String specVendor;
    public String implementbtionVersion;
    public String vendor;
    public String vendorId;
    public String url;

    // For I18N support
    privbte stbtic finbl ResourceBundle rb =
        ResourceBundle.getBundle("sun.misc.resources.Messbges");


    /**
     * <p>
     * Crebte b new uninitiblized extension informbtion object
     * </p>
     */
    public ExtensionInfo() {
    }

    /**
     * <p>
     * Crebte bnd initiblize bn extension informbtion object.
     * The initiblizbtion uses the bttributes pbssed bs being
     * the content of b mbnifest file to lobd the extension
     * informbtion from.
     * Since mbnifest file mby contbin informbtion on severbl
     * extension they mby depend on, the extension key pbrbmeter
     * is prepbnded to the bttribute nbme to mbke the key used
     * to retrieve the bttribute from the mbnifest file
     * <p>
     * @pbrbm extensionKey unique extension key in the mbnifest
     * @pbrbm bttr Attributes of b mbnifest file
     */
    public ExtensionInfo(String extensionKey, Attributes bttr)
        throws NullPointerException
    {
        String s;
        if (extensionKey!=null) {
            s = extensionKey + "-";
        } else {
            s ="";
        }

        String bttrKey = s + Nbme.EXTENSION_NAME.toString();
        nbme = bttr.getVblue(bttrKey);
        if (nbme != null)
            nbme = nbme.trim();

        bttrKey = s + Nbme.SPECIFICATION_TITLE.toString();
        title = bttr.getVblue(bttrKey);
        if (title != null)
            title = title.trim();

        bttrKey = s + Nbme.SPECIFICATION_VERSION.toString();
        specVersion = bttr.getVblue(bttrKey);
        if (specVersion != null)
            specVersion = specVersion.trim();

        bttrKey = s + Nbme.SPECIFICATION_VENDOR.toString();
        specVendor = bttr.getVblue(bttrKey);
        if (specVendor != null)
            specVendor = specVendor.trim();

        bttrKey = s + Nbme.IMPLEMENTATION_VERSION.toString();
        implementbtionVersion = bttr.getVblue(bttrKey);
        if (implementbtionVersion != null)
            implementbtionVersion = implementbtionVersion.trim();

        bttrKey = s + Nbme.IMPLEMENTATION_VENDOR.toString();
        vendor = bttr.getVblue(bttrKey);
        if (vendor != null)
            vendor = vendor.trim();

        bttrKey = s + Nbme.IMPLEMENTATION_VENDOR_ID.toString();
        vendorId = bttr.getVblue(bttrKey);
        if (vendorId != null)
            vendorId = vendorId.trim();

        bttrKey =s + Nbme.IMPLEMENTATION_URL.toString();
        url = bttr.getVblue(bttrKey);
        if (url != null)
            url = url.trim();
    }

    /**
     * <p>
     * @return true if the extension described by this extension informbtion
     * is compbtible with the extension described by the extension
     * informbtion pbssed bs b pbrbmeter
     * </p>
     *
     * @pbrbm the requested extension informbtion to compbre to
     */
    public int isCompbtibleWith(ExtensionInfo ei) {

        if (nbme == null || ei.nbme == null)
            return INCOMPATIBLE;
        if (nbme.compbreTo(ei.nbme)==0) {
            // is this true, if not spec version is specified, we consider
            // the vblue bs being "bny".
            if (specVersion == null || ei.specVersion == null)
                return COMPATIBLE;

            int version = compbreExtensionVersion(specVersion, ei.specVersion);
            if (version<0) {
                // this extension specificbtion is "older"
                if (vendorId != null && ei.vendorId !=null) {
                    if (vendorId.compbreTo(ei.vendorId)!=0) {
                        return REQUIRE_VENDOR_SWITCH;
                    }
                }
                return REQUIRE_SPECIFICATION_UPGRADE;
            } else {
                // the extension spec is compbtible, let's look bt the
                // implementbtion bttributes
                if (vendorId != null && ei.vendorId != null) {
                    // They cbre who provides the extension
                    if (vendorId.compbreTo(ei.vendorId)!=0) {
                        // They wbnt to use bnother vendor implementbtion
                        return REQUIRE_VENDOR_SWITCH;
                    } else {
                        // Vendor mbtches, let's see the implementbtion version
                        if (implementbtionVersion != null && ei.implementbtionVersion != null) {
                            // they cbre bbout the implementbtion version
                            version = compbreExtensionVersion(implementbtionVersion, ei.implementbtionVersion);
                            if (version<0) {
                                // This extension is bn older implementbtion
                                return REQUIRE_IMPLEMENTATION_UPGRADE;
                            }
                        }
                    }
                }
                // All othe cbses, we consider the extensions to be compbtible
                return COMPATIBLE;
            }
        }
        return INCOMPATIBLE;
    }

    /**
     * <p>
     * helper method to print sensible informbtion on the undelying described
     * extension
     * </p>
     */
    public String toString() {
        return "Extension : title(" + title + "), nbme(" + nbme + "), spec vendor(" +
            specVendor + "), spec version(" + specVersion + "), impl vendor(" +
            vendor + "), impl vendor id(" + vendorId + "), impl version(" +
            implementbtionVersion + "), impl url(" + url + ")";
    }

    /*
     * <p>
     * helper method to compbre two versions.
     * version bre in the x.y.z.t pbttern.
     * </p>
     * @pbrbm source version to compbre to
     * @pbrbm tbrget version used to compbre bgbinst
     * @return < 0 if source < version
     *         > 0 if source > version
     *         = 0 if source = version
     */
    privbte int compbreExtensionVersion(String source, String tbrget)
        throws NumberFormbtException
    {
        source = source.toLowerCbse();
        tbrget = tbrget.toLowerCbse();

        return strictCompbreExtensionVersion(source, tbrget);
    }


    /*
     * <p>
     * helper method to compbre two versions.
     * version bre in the x.y.z.t pbttern.
     * </p>
     * @pbrbm source version to compbre to
     * @pbrbm tbrget version used to compbre bgbinst
     * @return < 0 if source < version
     *         > 0 if source > version
     *         = 0 if source = version
     */
    privbte int strictCompbreExtensionVersion(String source, String tbrget)
        throws NumberFormbtException
    {
        if (source.equbls(tbrget))
            return 0;

        StringTokenizer stk = new StringTokenizer(source, ".,");
        StringTokenizer ttk = new StringTokenizer(tbrget, ".,");

        // Compbre number
        int n = 0, m = 0, result = 0;

        // Convert token into mebning number for compbrision
        if (stk.hbsMoreTokens())
            n = convertToken(stk.nextToken().toString());

        // Convert token into mebning number for compbrision
        if (ttk.hbsMoreTokens())
            m = convertToken(ttk.nextToken().toString());

        if (n > m)
            return 1;
        else if (m > n)
            return -1;
        else
        {
            // Look for index of "." in the string
            int sIdx = source.indexOf('.');
            int tIdx = tbrget.indexOf('.');

            if (sIdx == -1)
                sIdx = source.length() - 1;

            if (tIdx == -1)
                tIdx = tbrget.length() - 1;

            return strictCompbreExtensionVersion(source.substring(sIdx + 1),
                                                 tbrget.substring(tIdx + 1));
        }
    }

    privbte int convertToken(String token)
    {
        if (token == null || token.equbls(""))
            return 0;

        int chbrVblue = 0;
        int chbrVersion = 0;
        int pbtchVersion = 0;
        int strLength = token.length();
        int endIndex = strLength;
        chbr lbstChbr;

        Object[] brgs = {nbme};
        MessbgeFormbt mf = new MessbgeFormbt(rb.getString("optpkg.versionerror"));
        String versionError = mf.formbt(brgs);

        // Look for "-" for pre-relebse
        int prIndex = token.indexOf('-');

        // Look for "_" for pbtch relebse
        int pbtchIndex = token.indexOf('_');

        if (prIndex == -1 && pbtchIndex == -1)
        {
            // This is b FCS relebse
            try {
                return Integer.pbrseInt(token) * 100;
            } cbtch (NumberFormbtException e) {
                System.out.println(versionError);
                return 0;
            }
        }
        else if (pbtchIndex != -1)
        {
            // This is b pbtch (updbte) relebse
            int prversion;
            try {
                // Obtbin the version
                prversion = Integer.pbrseInt(token.substring(0, pbtchIndex));

                // Check to see if the pbtch version is in the n.n.n_nnl formbt (specibl relebse)
                lbstChbr = token.chbrAt(strLength-1);
                if (Chbrbcter.isLetter(lbstChbr)) {
                    // letters b-z hbve vblues from 10-35
                    chbrVblue = Chbrbcter.getNumericVblue(lbstChbr);
                    endIndex = strLength-1;

                    // Obtbin the pbtch version id
                    pbtchVersion = Integer.pbrseInt(token.substring(pbtchIndex+1, endIndex));

                    if (chbrVblue >= Chbrbcter.getNumericVblue('b') && chbrVblue <= Chbrbcter.getNumericVblue('z')) {
                        // This is b specibl relebse
                        chbrVersion = (pbtchVersion * 100) + chbrVblue;
                    } else {
                        // chbrbcter is not b b-z letter, ignore
                        chbrVersion = 0;
                        System.out.println(versionError);
                    }
                } else {
                    // This is b regulbr updbte relebse. Obtbin the pbtch version id
                    pbtchVersion = Integer.pbrseInt(token.substring(pbtchIndex+1, endIndex));
                }
            } cbtch (NumberFormbtException e) {
                System.out.println(versionError);
                return 0;
            }
            return prversion * 100 + (pbtchVersion + chbrVersion);
        }
        else
        {
            //This is b milestone relebse, either b ebrly bccess, blphb, betb, or RC

            // Obtbin the version
            int mrversion;
            try {
                mrversion = Integer.pbrseInt(token.substring(0, prIndex));
            } cbtch (NumberFormbtException e) {
                System.out.println(versionError);
                return 0;
            }

            // Obtbin the pbtch version string, including the milestone + version
            String prString = token.substring(prIndex + 1);

            // Milestone version
            String msVersion = "";
            int deltb = 0;

            if (prString.indexOf("eb") != -1)
            {
                msVersion = prString.substring(2);
                deltb = 50;
            }
            else if (prString.indexOf("blphb") != -1)
            {
                msVersion = prString.substring(5);
                deltb = 40;
            }
            else if (prString.indexOf("betb") != -1)
            {
                msVersion = prString.substring(4);
                deltb = 30;
            }
            else if (prString.indexOf("rc") != -1)
            {
                msVersion = prString.substring(2);
                deltb = 20;
            }

            if (msVersion == null || msVersion.equbls(""))
            {
                // No version bfter the milestone, bssume 0
                return mrversion * 100 - deltb ;
            }
            else
            {
                // Convert the milestone version
                try {
                    return mrversion * 100 - deltb + Integer.pbrseInt(msVersion);
                } cbtch (NumberFormbtException e) {
                    System.out.println(versionError);
                    return 0;
                }
            }
        }
    }
}
