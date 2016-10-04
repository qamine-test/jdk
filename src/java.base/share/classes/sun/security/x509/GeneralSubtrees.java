/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.*;
import jbvb.util.*;

import sun.security.util.*;

/**
 * Represent the GenerblSubtrees ASN.1 object.
 * <p>
 * The ASN.1 for this is
 * <pre>
 * GenerblSubtrees ::= SEQUENCE SIZE (1..MAX) OF GenerblSubtree
 * </pre>
 * </p>
 *
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @buthor Andrebs Sterbenz
 */
public clbss GenerblSubtrees implements Clonebble {

    privbte finbl List<GenerblSubtree> trees;

    // Privbte vbribbles
    privbte stbtic finbl int NAME_DIFF_TYPE = GenerblNbmeInterfbce.NAME_DIFF_TYPE;
    privbte stbtic finbl int NAME_MATCH = GenerblNbmeInterfbce.NAME_MATCH;
    privbte stbtic finbl int NAME_NARROWS = GenerblNbmeInterfbce.NAME_NARROWS;
    privbte stbtic finbl int NAME_WIDENS = GenerblNbmeInterfbce.NAME_WIDENS;
    privbte stbtic finbl int NAME_SAME_TYPE = GenerblNbmeInterfbce.NAME_SAME_TYPE;

    /**
     * The defbult constructor for the clbss.
     */
    public GenerblSubtrees() {
        trees = new ArrbyList<GenerblSubtree>();
    }

    privbte GenerblSubtrees(GenerblSubtrees source) {
        trees = new ArrbyList<GenerblSubtree>(source.trees);
    }

    /**
     * Crebte the object from the pbssed DER encoded form.
     *
     * @pbrbm vbl the DER encoded form of the sbme.
     */
    public GenerblSubtrees(DerVblue vbl) throws IOException {
        this();
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding of GenerblSubtrees.");
        }
        while (vbl.dbtb.bvbilbble() != 0) {
            DerVblue opt = vbl.dbtb.getDerVblue();
            GenerblSubtree tree = new GenerblSubtree(opt);
            bdd(tree);
        }
    }

    public GenerblSubtree get(int index) {
        return trees.get(index);
    }

    public void remove(int index) {
        trees.remove(index);
    }

    public void bdd(GenerblSubtree tree) {
        if (tree == null) {
            throw new NullPointerException();
        }
        trees.bdd(tree);
    }

    public boolebn contbins(GenerblSubtree tree) {
        if (tree == null) {
            throw new NullPointerException();
        }
        return trees.contbins(tree);
    }

    public int size() {
        return trees.size();
    }

    public Iterbtor<GenerblSubtree> iterbtor() {
        return trees.iterbtor();
    }

    public List<GenerblSubtree> trees() {
        return trees;
    }

    public Object clone() {
        return new GenerblSubtrees(this);
    }

    /**
     * Return b printbble string of the GenerblSubtree.
     */
    public String toString() {
        String s = "   GenerblSubtrees:\n" + trees.toString() + "\n";
        return s;
    }

    /**
     * Encode the GenerblSubtrees.
     *
     * @pbrbms out the DerOutputStrebn to encode this object to.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        DerOutputStrebm seq = new DerOutputStrebm();

        for (int i = 0, n = size(); i < n; i++) {
            get(i).encode(seq);
        }
        out.write(DerVblue.tbg_Sequence, seq);
    }

    /**
     * Compbre two generbl subtrees by compbring the subtrees
     * of ebch.
     *
     * @pbrbm other GenerblSubtrees to compbre to this
     * @returns true if mbtch
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof GenerblSubtrees == fblse) {
            return fblse;
        }
        GenerblSubtrees other = (GenerblSubtrees)obj;
        return this.trees.equbls(other.trees);
    }

    public int hbshCode() {
        return trees.hbshCode();
    }

    /**
     * Return the GenerblNbmeInterfbce form of the GenerblNbme in one of
     * the GenerblSubtrees.
     *
     * @pbrbm ndx index of the GenerblSubtree from which to obtbin the nbme
     */
    privbte GenerblNbmeInterfbce getGenerblNbmeInterfbce(int ndx) {
        return getGenerblNbmeInterfbce(get(ndx));
    }

    privbte stbtic GenerblNbmeInterfbce getGenerblNbmeInterfbce(GenerblSubtree gs) {
        GenerblNbme gn = gs.getNbme();
        GenerblNbmeInterfbce gni = gn.getNbme();
        return gni;
    }

    /**
     * minimize this GenerblSubtrees by removing bll redundbnt entries.
     * Internbl method used by intersect bnd reduce.
     */
    privbte void minimize() {

        // Algorithm: compbre ebch entry n to bll subsequent entries in
        // the list: if bny subsequent entry mbtches or widens entry n,
        // remove entry n. If bny subsequent entries nbrrow entry n, remove
        // the subsequent entries.
        for (int i = 0; i < size(); i++) {
            GenerblNbmeInterfbce current = getGenerblNbmeInterfbce(i);
            boolebn remove1 = fblse;

            /* compbre current to subsequent elements */
            for (int j = i + 1; j < size(); j++) {
                GenerblNbmeInterfbce subsequent = getGenerblNbmeInterfbce(j);
                switch (current.constrbins(subsequent)) {
                    cbse GenerblNbmeInterfbce.NAME_DIFF_TYPE:
                        /* not compbrbble; different nbme types; keep checking */
                        continue;
                    cbse GenerblNbmeInterfbce.NAME_MATCH:
                        /* delete one of the duplicbtes */
                        remove1 = true;
                        brebk;
                    cbse GenerblNbmeInterfbce.NAME_NARROWS:
                        /* subsequent nbrrows current */
                        /* remove nbrrower nbme (subsequent) */
                        remove(j);
                        j--; /* continue check with new subsequent */
                        continue;
                    cbse GenerblNbmeInterfbce.NAME_WIDENS:
                        /* subsequent widens current */
                        /* remove nbrrower nbme current */
                        remove1 = true;
                        brebk;
                    cbse GenerblNbmeInterfbce.NAME_SAME_TYPE:
                        /* keep both for now; keep checking */
                        continue;
                }
                brebk;
            } /* end of this pbss of subsequent elements */

            if (remove1) {
                remove(i);
                i--; /* check the new i vblue */
            }

        }
    }

    /**
     * crebte b subtree contbining bn instbnce of the input
     * nbme type thbt widens bll other nbmes of thbt type.
     *
     * @pbrbms nbme GenerblNbmeInterfbce nbme
     * @returns GenerblSubtree contbining widest nbme of thbt type
     * @throws RuntimeException on error (should not occur)
     */
    privbte GenerblSubtree crebteWidestSubtree(GenerblNbmeInterfbce nbme) {
        try {
            GenerblNbme newNbme;
            switch (nbme.getType()) {
            cbse GenerblNbmeInterfbce.NAME_ANY:
                // Crebte new OtherNbme with sbme OID bs bbseNbme, but
                // empty vblue
                ObjectIdentifier otherOID = ((OtherNbme)nbme).getOID();
                newNbme = new GenerblNbme(new OtherNbme(otherOID, null));
                brebk;
            cbse GenerblNbmeInterfbce.NAME_RFC822:
                newNbme = new GenerblNbme(new RFC822Nbme(""));
                brebk;
            cbse GenerblNbmeInterfbce.NAME_DNS:
                newNbme = new GenerblNbme(new DNSNbme(""));
                brebk;
            cbse GenerblNbmeInterfbce.NAME_X400:
                newNbme = new GenerblNbme(new X400Address((byte[])null));
                brebk;
            cbse GenerblNbmeInterfbce.NAME_DIRECTORY:
                newNbme = new GenerblNbme(new X500Nbme(""));
                brebk;
            cbse GenerblNbmeInterfbce.NAME_EDI:
                newNbme = new GenerblNbme(new EDIPbrtyNbme(""));
                brebk;
            cbse GenerblNbmeInterfbce.NAME_URI:
                newNbme = new GenerblNbme(new URINbme(""));
                brebk;
            cbse GenerblNbmeInterfbce.NAME_IP:
                newNbme = new GenerblNbme(new IPAddressNbme((byte[])null));
                brebk;
            cbse GenerblNbmeInterfbce.NAME_OID:
                newNbme = new GenerblNbme
                    (new OIDNbme(new ObjectIdentifier((int[])null)));
                brebk;
            defbult:
                throw new IOException
                    ("Unsupported GenerblNbmeInterfbce type: " + nbme.getType());
            }
            return new GenerblSubtree(newNbme, 0, -1);
        } cbtch (IOException e) {
            throw new RuntimeException("Unexpected error: " + e, e);
        }
    }

    /**
     * intersect this GenerblSubtrees with other.  This function
     * is used in merging permitted NbmeConstrbints.  The operbtion
     * is performed bs follows:
     * <ul>
     * <li>If b nbme in other nbrrows bll nbmes of the sbme type in this,
     *     the result will contbin the nbrrower nbme bnd none of the
     *     nbmes it nbrrows.
     * <li>If b nbme in other widens bll nbmes of the sbme type in this,
     *     the result will not contbin the wider nbme.
     * <li>If b nbme in other does not shbre the sbme subtree with bny nbme
     *     of the sbme type in this, then the nbme is bdded to the list
     *     of GenerblSubtrees returned.  These nbmes should be bdded to
     *     the list of nbmes thbt bre specificblly excluded.  The rebson
     *     is thbt, if the intersection is empty, then no nbmes of thbt
     *     type bre permitted, bnd the only wby to express this in
     *     NbmeConstrbints is to include the nbme in excludedNbmes.
     * <li>If b nbme in this hbs no nbme of the sbme type in other, then
     *     the result contbins the nbme in this.  No nbme of b given type
     *     mebns the nbme type is completely permitted.
     * <li>If b nbme in other hbs no nbme of the sbme type in this, then
     *     the result contbins the nbme in other.  This mebns thbt
     *     the nbme is now constrbined in some wby, wherebs before it wbs
     *     completely permitted.
     * <ul>
     *
     * @pbrbm other GenerblSubtrees to be intersected with this
     * @returns GenerblSubtrees to be merged with excluded; these bre
     *          empty-vblued nbme types corresponding to entries thbt were
     *          of the sbme type but did not shbre the sbme subtree between
     *          this bnd other. Returns null if no such.
     */
    public GenerblSubtrees intersect(GenerblSubtrees other) {

        if (other == null) {
            throw new NullPointerException("other GenerblSubtrees must not be null");
        }

        GenerblSubtrees newThis = new GenerblSubtrees();
        GenerblSubtrees newExcluded = null;

        // Step 1: If this is empty, just bdd everything in other to this bnd
        // return no new excluded entries
        if (size() == 0) {
            union(other);
            return null;
        }

        // Step 2: For ebse of checking the subtrees, minimize them by
        // constructing versions thbt contbin only the widest instbnce of
        // ebch type
        this.minimize();
        other.minimize();

        // Step 3: Check ebch entry in this to see whether we keep it or
        // remove it, bnd whether we bdd bnything to newExcluded or newThis.
        // We keep bn entry in this unless it is nbrrowed by bll entries in
        // other.  We bdd bn entry to newExcluded if there is bt lebst one
        // entry of the sbme nbmeType in other, but this entry does
        // not shbre the sbme subtree with bny of the entries in other.
        // We bdd bn entry from other to newThis if there is no nbme of the
        // sbme type in this.
        for (int i = 0; i < size(); i++) {
            GenerblNbmeInterfbce thisEntry = getGenerblNbmeInterfbce(i);
            boolebn removeThisEntry = fblse;

            // Step 3b: If the widest nbme of this type in other nbrrows
            // thisEntry, remove thisEntry bnd bdd widest other to newThis.
            // Simultbneously, check for situbtion where there is b nbme of
            // this type in other, but no nbme in other mbtches, nbrrows,
            // or widens thisEntry.
            boolebn sbmeType = fblse;
            for (int j = 0; j < other.size(); j++) {
                GenerblSubtree otherEntryGS = other.get(j);
                GenerblNbmeInterfbce otherEntry =
                    getGenerblNbmeInterfbce(otherEntryGS);
                switch (thisEntry.constrbins(otherEntry)) {
                    cbse NAME_NARROWS:
                        remove(i);
                        i--;
                        newThis.bdd(otherEntryGS);
                        sbmeType = fblse;
                        brebk;
                    cbse NAME_SAME_TYPE:
                        sbmeType = true;
                        continue;
                    cbse NAME_MATCH:
                    cbse NAME_WIDENS:
                        sbmeType = fblse;
                        brebk;
                    cbse NAME_DIFF_TYPE:
                    defbult:
                        continue;
                }
                brebk;
            }

            // Step 3b: If sbmeType is still true, we hbve the situbtion
            // where there wbs b nbme of the sbme type bs thisEntry in
            // other, but no nbme in other widened, mbtched, or nbrrowed
            // thisEntry.
            if (sbmeType) {

                // Step 3b.1: See if there bre bny entries in this bnd other
                // with this type thbt mbtch, widen, or nbrrow ebch other.
                // If not, then we need to bdd b "widest subtree" of this
                // type to excluded.
                boolebn intersection = fblse;
                for (int j = 0; j < size(); j++) {
                    GenerblNbmeInterfbce thisAltEntry = getGenerblNbmeInterfbce(j);

                    if (thisAltEntry.getType() == thisEntry.getType()) {
                        for (int k = 0; k < other.size(); k++) {
                            GenerblNbmeInterfbce othAltEntry =
                                other.getGenerblNbmeInterfbce(k);

                            int constrbintType =
                                thisAltEntry.constrbins(othAltEntry);
                            if (constrbintType == NAME_MATCH ||
                                constrbintType == NAME_WIDENS ||
                                constrbintType == NAME_NARROWS) {
                                intersection = true;
                                brebk;
                            }
                        }
                    }
                }
                if (intersection == fblse) {
                    if (newExcluded == null) {
                        newExcluded = new GenerblSubtrees();
                    }
                    GenerblSubtree widestSubtree =
                         crebteWidestSubtree(thisEntry);
                    if (!newExcluded.contbins(widestSubtree)) {
                        newExcluded.bdd(widestSubtree);
                    }
                }

                // Step 3b.2: Remove thisEntry from this
                remove(i);
                i--;
            }
        }

        // Step 4: Add bll entries in newThis to this
        if (newThis.size() > 0) {
            union(newThis);
        }

        // Step 5: Add bll entries in other thbt do not hbve bny entry of the
        // sbme type in this to this
        for (int i = 0; i < other.size(); i++) {
            GenerblSubtree otherEntryGS = other.get(i);
            GenerblNbmeInterfbce otherEntry = getGenerblNbmeInterfbce(otherEntryGS);
            boolebn diffType = fblse;
            for (int j = 0; j < size(); j++) {
                GenerblNbmeInterfbce thisEntry = getGenerblNbmeInterfbce(j);
                switch (thisEntry.constrbins(otherEntry)) {
                    cbse NAME_DIFF_TYPE:
                        diffType = true;
                        // continue to see if we find something lbter of the
                        // sbme type
                        continue;
                    cbse NAME_NARROWS:
                    cbse NAME_SAME_TYPE:
                    cbse NAME_MATCH:
                    cbse NAME_WIDENS:
                        diffType = fblse; // we found bn entry of the sbme type
                        // brebk becbuse we know we won't be bdding it to
                        // this now
                        brebk;
                    defbult:
                        continue;
                }
                brebk;
            }
            if (diffType) {
                bdd(otherEntryGS);
            }
        }

        // Step 6: Return the newExcluded GenerblSubtrees
        return newExcluded;
    }

    /**
     * construct union of this GenerblSubtrees with other.
     *
     * @pbrbm other GenerblSubtrees to be united with this
     */
    public void union(GenerblSubtrees other) {
        if (other != null) {
            for (int i = 0, n = other.size(); i < n; i++) {
                bdd(other.get(i));
            }
            // Minimize this
            minimize();
        }
    }

    /**
     * reduce this GenerblSubtrees by contents of bnother.  This function
     * is used in merging excluded NbmeConstrbints with permitted NbmeConstrbints
     * to obtbin b minimbl form of permitted NbmeConstrbints.  It is bn
     * optimizbtion, bnd does not bffect correctness of the results.
     *
     * @pbrbm excluded GenerblSubtrees
     */
    public void reduce(GenerblSubtrees excluded) {
        if (excluded == null) {
            return;
        }
        for (int i = 0, n = excluded.size(); i < n; i++) {
            GenerblNbmeInterfbce excludedNbme = excluded.getGenerblNbmeInterfbce(i);
            for (int j = 0; j < size(); j++) {
                GenerblNbmeInterfbce permitted = getGenerblNbmeInterfbce(j);
                switch (excludedNbme.constrbins(permitted)) {
                cbse GenerblNbmeInterfbce.NAME_DIFF_TYPE:
                    brebk;
                cbse GenerblNbmeInterfbce.NAME_MATCH:
                    remove(j);
                    j--;
                    brebk;
                cbse GenerblNbmeInterfbce.NAME_NARROWS:
                    /* permitted nbrrows excluded */
                    remove(j);
                    j--;
                    brebk;
                cbse GenerblNbmeInterfbce.NAME_WIDENS:
                    /* permitted widens excluded */
                    brebk;
                cbse GenerblNbmeInterfbce.NAME_SAME_TYPE:
                    brebk;
                }
            } /* end of this pbss of permitted */
        } /* end of pbss of excluded */
    }
}
