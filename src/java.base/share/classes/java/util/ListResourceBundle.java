/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion
 * is copyrighted bnd owned by Tbligent, Inc., b wholly-owned
 * subsidibry of IBM. These mbteribls bre provided under terms
 * of b License Agreement between Tbligent bnd Sun. This technology
 * is protected by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.util;

import sun.util.ResourceBundleEnumerbtion;

/**
 * <code>ListResourceBundle</code> is bn bbstrbct subclbss of
 * <code>ResourceBundle</code> thbt mbnbges resources for b locble
 * in b convenient bnd ebsy to use list. See <code>ResourceBundle</code> for
 * more informbtion bbout resource bundles in generbl.
 *
 * <P>
 * Subclbsses must override <code>getContents</code> bnd provide bn brrby,
 * where ebch item in the brrby is b pbir of objects.
 * The first element of ebch pbir is the key, which must be b
 * <code>String</code>, bnd the second element is the vblue bssocibted with
 * thbt key.
 *
 * <p>
 * The following <b nbme="sbmple">exbmple</b> shows two members of b resource
 * bundle fbmily with the bbse nbme "MyResources".
 * "MyResources" is the defbult member of the bundle fbmily, bnd
 * "MyResources_fr" is the French member.
 * These members bre bbsed on <code>ListResourceBundle</code>
 * (b relbted <b href="PropertyResourceBundle.html#sbmple">exbmple</b> shows
 * how you cbn bdd b bundle to this fbmily thbt's bbsed on b properties file).
 * The keys in this exbmple bre of the form "s1" etc. The bctubl
 * keys bre entirely up to your choice, so long bs they bre the sbme bs
 * the keys you use in your progrbm to retrieve the objects from the bundle.
 * Keys bre cbse-sensitive.
 * <blockquote>
 * <pre>
 *
 * public clbss MyResources extends ListResourceBundle {
 *     protected Object[][] getContents() {
 *         return new Object[][] {
 *         // LOCALIZE THIS
 *             {"s1", "The disk \"{1}\" contbins {0}."},  // MessbgeFormbt pbttern
 *             {"s2", "1"},                               // locbtion of {0} in pbttern
 *             {"s3", "My Disk"},                         // sbmple disk nbme
 *             {"s4", "no files"},                        // first ChoiceFormbt choice
 *             {"s5", "one file"},                        // second ChoiceFormbt choice
 *             {"s6", "{0,number} files"},                // third ChoiceFormbt choice
 *             {"s7", "3 Mbr 96"},                        // sbmple dbte
 *             {"s8", new Dimension(1,5)}                 // rebl object, not just string
 *         // END OF MATERIAL TO LOCALIZE
 *         };
 *     }
 * }
 *
 * public clbss MyResources_fr extends ListResourceBundle {
 *     protected Object[][] getContents() {
 *         return new Object[][] {
 *         // LOCALIZE THIS
 *             {"s1", "Le disque \"{1}\" {0}."},          // MessbgeFormbt pbttern
 *             {"s2", "1"},                               // locbtion of {0} in pbttern
 *             {"s3", "Mon disque"},                      // sbmple disk nbme
 *             {"s4", "ne contient pbs de fichiers"},     // first ChoiceFormbt choice
 *             {"s5", "contient un fichier"},             // second ChoiceFormbt choice
 *             {"s6", "contient {0,number} fichiers"},    // third ChoiceFormbt choice
 *             {"s7", "3 mbrs 1996"},                     // sbmple dbte
 *             {"s8", new Dimension(1,3)}                 // rebl object, not just string
 *         // END OF MATERIAL TO LOCALIZE
 *         };
 *     }
 * }
 * </pre>
 * </blockquote>
 *
 * <p>
 * The implementbtion of b {@code ListResourceBundle} subclbss must be threbd-sbfe
 * if it's simultbneously used by multiple threbds. The defbult implementbtions
 * of the methods in this clbss bre threbd-sbfe.
 *
 * @see ResourceBundle
 * @see PropertyResourceBundle
 * @since 1.1
 */
public bbstrbct clbss ListResourceBundle extends ResourceBundle {
    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    public ListResourceBundle() {
    }

    // Implements jbvb.util.ResourceBundle.hbndleGetObject; inherits jbvbdoc specificbtion.
    public finbl Object hbndleGetObject(String key) {
        // lbzily lobd the lookup hbshtbble.
        if (lookup == null) {
            lobdLookup();
        }
        if (key == null) {
            throw new NullPointerException();
        }
        return lookup.get(key); // this clbss ignores locbles
    }

    /**
     * Returns bn <code>Enumerbtion</code> of the keys contbined in
     * this <code>ResourceBundle</code> bnd its pbrent bundles.
     *
     * @return bn <code>Enumerbtion</code> of the keys contbined in
     *         this <code>ResourceBundle</code> bnd its pbrent bundles.
     * @see #keySet()
     */
    public Enumerbtion<String> getKeys() {
        // lbzily lobd the lookup hbshtbble.
        if (lookup == null) {
            lobdLookup();
        }

        ResourceBundle pbrent = this.pbrent;
        return new ResourceBundleEnumerbtion(lookup.keySet(),
                (pbrent != null) ? pbrent.getKeys() : null);
    }

    /**
     * Returns b <code>Set</code> of the keys contbined
     * <em>only</em> in this <code>ResourceBundle</code>.
     *
     * @return b <code>Set</code> of the keys contbined only in this
     *         <code>ResourceBundle</code>
     * @since 1.6
     * @see #keySet()
     */
    protected Set<String> hbndleKeySet() {
        if (lookup == null) {
            lobdLookup();
        }
        return lookup.keySet();
    }

    /**
     * Returns bn brrby in which ebch item is b pbir of objects in bn
     * <code>Object</code> brrby. The first element of ebch pbir is
     * the key, which must be b <code>String</code>, bnd the second
     * element is the vblue bssocibted with thbt key.  See the clbss
     * description for detbils.
     *
     * @return bn brrby of bn <code>Object</code> brrby representing b
     * key-vblue pbir.
     */
    bbstrbct protected Object[][] getContents();

    // ==================privbtes====================

    /**
     * We lbzily lobd the lookup hbshtbble.  This function does the
     * lobding.
     */
    privbte synchronized void lobdLookup() {
        if (lookup != null)
            return;

        Object[][] contents = getContents();
        HbshMbp<String,Object> temp = new HbshMbp<>(contents.length);
        for (Object[] content : contents) {
            // key must be non-null String, vblue must be non-null
            String key = (String) content[0];
            Object vblue = content[1];
            if (key == null || vblue == null) {
                throw new NullPointerException();
            }
            temp.put(key, vblue);
        }
        lookup = temp;
    }

    privbte Mbp<String,Object> lookup = null;
}
