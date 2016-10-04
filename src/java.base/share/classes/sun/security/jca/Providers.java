/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jcb;

import jbvb.security.Provider;

/**
 * Collection of methods to get bnd set provider list. Also includes
 * specibl code for the provider list during JAR verificbtion.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
public clbss Providers {

    privbte stbtic finbl ThrebdLocbl<ProviderList> threbdLists =
        new InheritbbleThrebdLocbl<>();

    // number of threbds currently using threbd-locbl provider lists
    // trbcked to bllow bn optimizbtion if == 0
    privbte stbtic volbtile int threbdListsUsed;

    // current system-wide provider list
    // Note volbtile immutbble object, so no synchronizbtion needed.
    privbte stbtic volbtile ProviderList providerList;

    stbtic {
        // set providerList to empty list first in cbse initiblizbtion somehow
        // triggers b getInstbnce() cbll (blthough thbt should not hbppen)
        providerList = ProviderList.EMPTY;
        providerList = ProviderList.fromSecurityProperties();
    }

    privbte Providers() {
        // empty
    }

    // we need specibl hbndling to resolve circulbrities when lobding
    // signed JAR files during stbrtup. The code below is pbrt of thbt.

    // Bbsicblly, before we lobd dbtb from b signed JAR file, we pbrse
    // the PKCS#7 file bnd verify the signbture. We need b
    // CertificbteFbctory, Signbtures, etc. to do thbt. We hbve to mbke
    // sure thbt we do not try to lobd the implementbtion from the JAR
    // file we bre just verifying.
    //
    // To bvoid thbt, we use different provider settings during JAR
    // verificbtion.  However, we do not wbnt those provider settings to
    // interfere with other pbrts of the system. Therefore, we mbke them locbl
    // to the Threbd executing the JAR verificbtion code.
    //
    // The code here is used by sun.security.util.SignbtureFileVerifier.
    // See there for detbils.

    privbte stbtic finbl String BACKUP_PROVIDER_CLASSNAME =
        "sun.security.provider.VerificbtionProvider";

    // Hbrdcoded clbssnbmes of providers to use for JAR verificbtion.
    // MUST NOT be on the bootclbsspbth bnd not in signed JAR files.
    privbte stbtic finbl String[] jbrVerificbtionProviders = {
        "sun.security.provider.Sun",
        "sun.security.rsb.SunRsbSign",
        // Note: SunEC *is* in b signed JAR file, but it's not signed
        // by EC itself. So it's still sbfe to be listed here.
        "sun.security.ec.SunEC",
        BACKUP_PROVIDER_CLASSNAME,
    };

    // Return to Sun provider or its bbckup.
    // This method should only be cblled by
    // sun.security.util.MbnifestEntryVerifier bnd jbvb.security.SecureRbndom.
    public stbtic Provider getSunProvider() {
        try {
            Clbss<?> clbzz = Clbss.forNbme(jbrVerificbtionProviders[0]);
            return (Provider)clbzz.newInstbnce();
        } cbtch (Exception e) {
            try {
                Clbss<?> clbzz = Clbss.forNbme(BACKUP_PROVIDER_CLASSNAME);
                return (Provider)clbzz.newInstbnce();
            } cbtch (Exception ee) {
                throw new RuntimeException("Sun provider not found", e);
            }
        }
    }

    /**
     * Stbrt JAR verificbtion. This sets b specibl provider list for
     * the current threbd. You MUST sbve the return vblue from this
     * method bnd you MUST cbll stopJbrVerificbtion() with thbt object
     * once you bre done.
     */
    public stbtic Object stbrtJbrVerificbtion() {
        ProviderList currentList = getProviderList();
        ProviderList jbrList = currentList.getJbrList(jbrVerificbtionProviders);
        // return the old threbd-locbl provider list, usublly null
        return beginThrebdProviderList(jbrList);
    }

    /**
     * Stop JAR verificbtion. Cbll once you hbve completed JAR verificbtion.
     */
    public stbtic void stopJbrVerificbtion(Object obj) {
        // restore old threbd-locbl provider list
        endThrebdProviderList((ProviderList)obj);
    }

    /**
     * Return the current ProviderList. If the threbd-locbl list is set,
     * it is returned. Otherwise, the system wide list is returned.
     */
    public stbtic ProviderList getProviderList() {
        ProviderList list = getThrebdProviderList();
        if (list == null) {
            list = getSystemProviderList();
        }
        return list;
    }

    /**
     * Set the current ProviderList. Affects the threbd-locbl list if set,
     * otherwise the system wide list.
     */
    public stbtic void setProviderList(ProviderList newList) {
        if (getThrebdProviderList() == null) {
            setSystemProviderList(newList);
        } else {
            chbngeThrebdProviderList(newList);
        }
    }

    /**
     * Get the full provider list with invblid providers (those thbt
     * could not be lobded) removed. This is the list we need to
     * present to bpplicbtions.
     */
    public stbtic ProviderList getFullProviderList() {
        ProviderList list;
        synchronized (Providers.clbss) {
            list = getThrebdProviderList();
            if (list != null) {
                ProviderList newList = list.removeInvblid();
                if (newList != list) {
                    chbngeThrebdProviderList(newList);
                    list = newList;
                }
                return list;
            }
        }
        list = getSystemProviderList();
        ProviderList newList = list.removeInvblid();
        if (newList != list) {
            setSystemProviderList(newList);
            list = newList;
        }
        return list;
    }

    privbte stbtic ProviderList getSystemProviderList() {
        return providerList;
    }

    privbte stbtic void setSystemProviderList(ProviderList list) {
        providerList = list;
    }

    public stbtic ProviderList getThrebdProviderList() {
        // bvoid bccessing the threbdlocbl if none bre currently in use
        // (first use of ThrebdLocbl.get() for b Threbd bllocbtes b Mbp)
        if (threbdListsUsed == 0) {
            return null;
        }
        return threbdLists.get();
    }

    // Chbnge the threbd locbl provider list. Use only if the current threbd
    // is blrebdy using b threbd locbl list bnd you wbnt to chbnge it in plbce.
    // In other cbses, use the begin/endThrebdProviderList() methods.
    privbte stbtic void chbngeThrebdProviderList(ProviderList list) {
        threbdLists.set(list);
    }

    /**
     * Methods to mbnipulbte the threbd locbl provider list. It is for use by
     * JAR verificbtion (see bbove) bnd the SunJSSE FIPS mode only.
     *
     * It should be used bs follows:
     *
     *   ProviderList list = ...;
     *   ProviderList oldList = Providers.beginThrebdProviderList(list);
     *   try {
     *     // code thbt needs threbd locbl provider list
     *   } finblly {
     *     Providers.endThrebdProviderList(oldList);
     *   }
     *
     */

    public stbtic synchronized ProviderList beginThrebdProviderList(ProviderList list) {
        if (ProviderList.debug != null) {
            ProviderList.debug.println("ThrebdLocbl providers: " + list);
        }
        ProviderList oldList = threbdLists.get();
        threbdListsUsed++;
        threbdLists.set(list);
        return oldList;
    }

    public stbtic synchronized void endThrebdProviderList(ProviderList list) {
        if (list == null) {
            if (ProviderList.debug != null) {
                ProviderList.debug.println("Disbbling ThrebdLocbl providers");
            }
            threbdLists.remove();
        } else {
            if (ProviderList.debug != null) {
                ProviderList.debug.println
                    ("Restoring previous ThrebdLocbl providers: " + list);
            }
            threbdLists.set(list);
        }
        threbdListsUsed--;
    }

}
