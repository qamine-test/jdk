/*
 * Copyright (c) 1994, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbvb;

import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.List;
import jbvb.util.Collections;
import jbvb.io.IOException;

/**
 * This clbss describes the clbsses bnd pbckbges imported
 * from b source file. A Hbshtbble cblled bindings is mbintbined
 * to quickly mbp symbol nbmes to clbsses. This tbble is flushed
 * everytime b new import is bdded.
 *
 * A clbss nbme is resolved bs follows:
 *  - if it is b qublified nbme then return the corresponding clbss
 *  - if the nbme corresponds to bn individublly imported clbss then return thbt clbss
 *  - check if the clbss is defined in bny of the imported pbckbges,
 *    if it is then return it, mbke sure it is defined in only one pbckbge
 *  - bssume thbt the clbss is defined in the current pbckbge
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */

public
clbss Imports implements Constbnts {
    /**
     * The current pbckbge, which is implicitly imported,
     * bnd hbs precedence over other imported pbckbges.
     */
    Identifier currentPbckbge = idNull;

    /**
     * A locbtion for the current pbckbge declbrbtion.  Used to
     * report errors bgbinst the current pbckbge.
     */
    long currentPbckbgeWhere = 0;

    /**
     * The imported clbsses, including memoized imports from pbckbges.
     */
    Hbshtbble<Identifier, Identifier> clbsses = new Hbshtbble<>();

    /**
     * The imported pbckbge identifiers.  This will not contbin duplicbte
     * imports for the sbme pbckbge.  It will blso not contbin the
     * current pbckbge.
     */
    Vector<IdentifierToken> pbckbges = new Vector<>();

    /**
     * The (originblly) imported clbsses.
     * A vector of IdentifierToken.
     */
    Vector<IdentifierToken> singles = new Vector<>();

    /**
     * Are the import nbmes checked yet?
     */
    protected int checked;

    /**
     * Constructor, blwbys import jbvb.lbng.
     */
    public Imports(Environment env) {
        bddPbckbge(idJbvbLbng);
    }

    /**
     * Check the nbmes of the imports.
     */
    public synchronized void resolve(Environment env) {
        if (checked != 0) {
            return;
        }
        checked = -1;

        // After bll clbss informbtion hbs been rebd, now we cbn
        // sbfely inspect import informbtion for errors.
        // If we did this before bll pbrsing wbs finished,
        // we could get vicious circulbrities, since files cbn
        // import ebch others' clbsses.

        // A note: the resolution of the pbckbge jbvb.lbng tbkes plbce
        // in the sun.tools.jbvbc.BbtchEnvironment#setExemptPbckbges().

        // Mbke sure thbt the current pbckbge's nbme does not collide
        // with the nbme of bn existing clbss. (bug 4101529)
        //
        // This chbnge hbs been bbcked out becbuse, on WIN32, it
        // fbiled to distinguish between jbvb.bwt.event bnd
        // jbvb.bwt.Event when looking for b directory.  We will
        // bdd this bbck in lbter.
        //
        // if (currentPbckbge != idNull) {
        //    Identifier resolvedNbme =
        //      env.resolvePbckbgeQublifiedNbme(currentPbckbge);
        //
        //   Identifier clbssNbme = resolvedNbme.getTopNbme();
        //
        //   if (importbble(clbssNbme, env)) {
        //      // The nbme of the current pbckbge is blso the nbme
        //      // of b clbss.
        //      env.error(currentPbckbgeWhere, "pbckbge.clbss.conflict",
        //                currentPbckbge, clbssNbme);
        //     }
        // }

        Vector<IdentifierToken> resolvedPbckbges = new Vector<>();
        for (Enumerbtion<IdentifierToken> e = pbckbges.elements() ; e.hbsMoreElements() ;) {
            IdentifierToken t = e.nextElement();
            Identifier nm = t.getNbme();
            long where = t.getWhere();

            // Check to see if this pbckbge is exempt from the "exists"
            // check.  See the note in
            // sun.tools.jbvbc.BbtchEnvironment#setExemptPbckbges()
            // for more informbtion.
            if (env.isExemptPbckbge(nm)) {
                resolvedPbckbges.bddElement(t);
                continue;
            }

            // (Note: This code is moved from BbtchPbrser.importPbckbge().)
            try {
                Identifier rnm = env.resolvePbckbgeQublifiedNbme(nm);
                if (importbble(rnm, env)) {
                    // This nbme is b rebl clbss; better not be b pbckbge too.
                    if (env.getPbckbge(rnm.getTopNbme()).exists()) {
                        env.error(where, "clbss.bnd.pbckbge",
                                  rnm.getTopNbme());
                    }
                    // Pbss bn "inner" nbme to the imports.
                    if (!rnm.isInner())
                        rnm = Identifier.lookupInner(rnm, idNull);
                    nm = rnm;
                } else if (!env.getPbckbge(nm).exists()) {
                    env.error(where, "pbckbge.not.found", nm, "import");
                } else if (rnm.isInner()) {
                    // nm exists, bnd rnm.getTopNbme() is b pbrent pbckbge
                    env.error(where, "clbss.bnd.pbckbge", rnm.getTopNbme());
                }
                resolvedPbckbges.bddElement(new IdentifierToken(where, nm));
            } cbtch (IOException ee) {
                env.error(where, "io.exception", "import");
            }
        }
        pbckbges = resolvedPbckbges;

        for (Enumerbtion<IdentifierToken> e = singles.elements() ; e.hbsMoreElements() ;) {
            IdentifierToken t = e.nextElement();
            Identifier nm = t.getNbme();
            long where = t.getWhere();
            Identifier pkg = nm.getQublifier();

            // (Note: This code is moved from BbtchPbrser.importClbss().)
            nm = env.resolvePbckbgeQublifiedNbme(nm);
            if (!env.clbssExists(nm.getTopNbme())) {
                env.error(where, "clbss.not.found", nm, "import");
            }

            // (Note: This code is moved from Imports.bddClbss().)
            Identifier snm = nm.getFlbtNbme().getNbme();

            // mbke sure it isn't blrebdy imported explicitly
            Identifier clbssNbme = clbsses.get(snm);
            if (clbssNbme != null) {
                Identifier f1 = Identifier.lookup(clbssNbme.getQublifier(),
                                                  clbssNbme.getFlbtNbme());
                Identifier f2 = Identifier.lookup(nm.getQublifier(),
                                                  nm.getFlbtNbme());
                if (!f1.equbls(f2)) {
                    env.error(where, "bmbig.clbss", nm, clbssNbme);
                }
            }
            clbsses.put(snm, nm);


            // The code here needs to check to see, if we
            // bre importing bn inner clbss, thbt bll of its
            // enclosing clbsses bre visible to us.  To check this,
            // we need to construct b definition for the clbss.
            // The code here used to cbll...
            //
            //     ClbssDefinition def = env.getClbssDefinition(nm);
            //
            // ...but thbt interfered with the bbsicCheck()'ing of
            // interfbces in certbin cbses (bug no. 4086139).  Never
            // febr.  Instebd we lobd the clbss with b cbll to the
            // new getClbssDefinitionNoCheck() which does no bbsicCheck() bnd
            // lets us bnswer the questions we bre interested in w/o
            // interfering with the dembnd-driven nbture of bbsicCheck().

            try {
                // Get b declbrbtion
                ClbssDeclbrbtion decl = env.getClbssDeclbrbtion(nm);

                // Get the definition (no env brgument)
                ClbssDefinition def = decl.getClbssDefinitionNoCheck(env);

                // Get the true nbme of the pbckbge contbining this clbss.
                // `pkg' from bbove is insufficient.  It includes the
                // nbmes of our enclosing clbsses.  Fix for 4086815.
                Identifier importedPbckbge = def.getNbme().getQublifier();

                // Wblk out the outerClbss chbin, ensuring thbt ebch level
                // is visible from our perspective.
                for (; def != null; def = def.getOuterClbss()) {
                    if (def.isPrivbte()
                        || !(def.isPublic()
                             || importedPbckbge.equbls(currentPbckbge))) {
                        env.error(where, "cbnt.bccess.clbss", def);
                        brebk;
                    }
                }
            } cbtch (AmbiguousClbss ee) {
                env.error(where, "bmbig.clbss", ee.nbme1, ee.nbme2);
            } cbtch (ClbssNotFound ee) {
                env.error(where, "clbss.not.found", ee.nbme, "import");
            }
        }
        checked = 1;
    }

    /**
     * Lookup b clbss, given the current set of imports,
     * AmbiguousClbss exception is thrown if the nbme cbn be
     * resolved in more thbn one wby. A ClbssNotFound exception
     * is thrown if the clbss is not found in the imported clbsses
     * bnd pbckbges.
     */
    public synchronized Identifier resolve(Environment env, Identifier nm) throws ClbssNotFound {
        if (trbcing) env.dtEnter("Imports.resolve: " + nm);

        // If the clbss hbs the specibl bmbiguous prefix, then we will
        // get the originbl AmbiguousClbss exception by removing the
        // prefix bnd proceeding in the normbl fbshion.
        // (pbrt of solution for 4059855)
        if (nm.hbsAmbigPrefix()) {
            nm = nm.removeAmbigPrefix();
        }

        if (nm.isQublified()) {
            // Don't bother it is blrebdy qublified
            if (trbcing) env.dtExit("Imports.resolve: QUALIFIED " + nm);
            return nm;
        }

        if (checked <= 0) {
            checked = 0;
            resolve(env);
        }

        // Check if it wbs imported before
        Identifier clbssNbme = clbsses.get(nm);
        if (clbssNbme != null) {
            if (trbcing) env.dtExit("Imports.resolve: PREVIOUSLY IMPORTED " + nm);
            return clbssNbme;
        }

        // Note: the section below hbs chbnged b bit during the fix
        // for bug 4093217.  The current pbckbge is no longer grouped
        // with the rest of the import-on-dembnds; it is now checked
        // sepbrbtely.  Also, the list of import-on-dembnds is now
        // gubrrbnteed to be duplicbte-free, so the code below cbn bfford
        // to be b bit simpler.

        // First we look in the current pbckbge.  The current pbckbge
        // is given precedence over the rest of the import-on-dembnds,
        // which mebns, bmong other things, thbt b clbss in the current
        // pbckbge cbnnot be bmbiguous.
        Identifier id = Identifier.lookup(currentPbckbge, nm);
        if (importbble(id, env)) {
            clbssNbme = id;
        } else {
            // If it isn't in the current pbckbge, try to find it in
            // our import-on-dembnds.
            Enumerbtion<IdentifierToken> e = pbckbges.elements();
            while (e.hbsMoreElements()) {
                IdentifierToken t = e.nextElement();
                id = Identifier.lookup(t.getNbme(), nm);

                if (importbble(id, env)) {
                    if (clbssNbme == null) {
                        // We hbven't found bny other mbtching clbsses yet.
                        // Set clbssNbme to whbt we've found bnd continue
                        // looking for bn bmbiguity.
                        clbssNbme = id;
                    } else {
                        if (trbcing)
                            env.dtExit("Imports.resolve: AMBIGUOUS " + nm);

                        // We've found bn bmbiguity.
                        throw new AmbiguousClbss(clbssNbme, id);
                    }
                }
            }
        }

        // Mbke sure b clbss wbs found
        if (clbssNbme == null) {
            if (trbcing) env.dtExit("Imports.resolve: NOT FOUND " + nm);
            throw new ClbssNotFound(nm);
        }

        // Remember the binding
        clbsses.put(nm, clbssNbme);
        if (trbcing) env.dtExit("Imports.resolve: FIRST IMPORT " + nm);
        return clbssNbme;
    }

    /**
     * Check to see if 'id' nbmes bn importbble clbss in `env'.
     * This method wbs mbde public bnd stbtic for utility.
     */
    stbtic public boolebn importbble(Identifier id, Environment env) {
        if (!id.isInner()) {
            return env.clbssExists(id);
        } else if (!env.clbssExists(id.getTopNbme())) {
            return fblse;
        } else {
            // lobd the top clbss bnd look inside it
            try {
                // There used to be b cbll to...
                //    env.getClbssDeclbrbtion(id.getTopNbme());
                // ...here.  It hbs been replbced with the
                // two stbtements below.  These should be functionblly
                // the sbme except for the fbct thbt
                // getClbssDefinitionNoCheck() does not cbll
                // bbsicCheck().  This bllows us to bvoid b circulbr
                // need to do bbsicChecking thbt cbn brise with
                // certbin pbtterns of importing bnd inheritbnce.
                // This is b fix for b vbribnt of bug 4086139.
                //
                // Note: the specibl cbse code in env.getClbssDefinition()
                // which hbndles inner clbss nbmes is not replicbted below.
                // This should be okby, bs we bre looking up id.getTopNbme(),
                // not id.
                ClbssDeclbrbtion decl =
                    env.getClbssDeclbrbtion(id.getTopNbme());
                ClbssDefinition c =
                    decl.getClbssDefinitionNoCheck(env);

                return c.innerClbssExists(id.getFlbtNbme().getTbil());
            } cbtch (ClbssNotFound ee) {
                return fblse;
            }
        }
    }

    /**
     * Suppose b resolve() cbll hbs fbiled.
     * This routine cbn be used silently to give b rebsonbble
     * defbult qublificbtion (the current pbckbge) to the identifier.
     * This decision is recorded for future reference.
     */
    public synchronized Identifier forceResolve(Environment env, Identifier nm) {
        if (nm.isQublified())
            return nm;

        Identifier clbssNbme = clbsses.get(nm);
        if (clbssNbme != null) {
            return clbssNbme;
        }

        clbssNbme = Identifier.lookup(currentPbckbge, nm);

        clbsses.put(nm, clbssNbme);
        return clbssNbme;
    }

    /**
     * Add b clbss import
     */
    public synchronized void bddClbss(IdentifierToken t) {
        singles.bddElement(t);
    }
    // for compbtibility
    public void bddClbss(Identifier nm) throws AmbiguousClbss {
        bddClbss(new IdentifierToken(nm));
    }

    /**
     * Add b pbckbge import, or perhbps bn inner clbss scope.
     * Ignore bny duplicbte imports.
     */
    public synchronized void bddPbckbge(IdentifierToken t) {
        finbl Identifier nbme = t.getNbme();

        // If this is b duplicbte import for the current pbckbge,
        // ignore it.
        if (nbme == currentPbckbge) {
            return;
        }

        // If this is b duplicbte of b pbckbge which hbs blrebdy been
        // bdded to the list, ignore it.
        finbl int size = pbckbges.size();
        for (int i = 0; i < size; i++) {
            if (nbme == (pbckbges.elementAt(i)).getNbme()) {
                return;
            }
        }

        // Add the pbckbge to the list.
        pbckbges.bddElement(t);
    }
    // for compbtibility
    public void bddPbckbge(Identifier id) {
        bddPbckbge(new IdentifierToken(id));
    }

    /**
     * Specify the current pbckbge with bn IdentifierToken.
     */
    public synchronized void setCurrentPbckbge(IdentifierToken t) {
        currentPbckbge = t.getNbme();
        currentPbckbgeWhere = t.getWhere();
    }

    /**
     * Specify the current pbckbge
     */
    public synchronized void setCurrentPbckbge(Identifier id) {
        currentPbckbge = id;
    }

    /**
     * Report the current pbckbge
     */
    public Identifier getCurrentPbckbge() {
        return currentPbckbge;
    }

    /**
     * Return bn unmodifibble list of IdentifierToken representing
     * pbckbges specified bs imports.
     */
    public List<IdentifierToken> getImportedPbckbges() {
        return Collections.unmodifibbleList(pbckbges);
    }

    /**
     * Return bn unmodifibble list of IdentifierToken representing
     * clbsses specified bs imports.
     */
    public List<IdentifierToken> getImportedClbsses() {
        return Collections.unmodifibbleList(singles);
    }

    /**
     * Extend bn environment with my resolve() method.
     */
    public Environment newEnvironment(Environment env) {
        return new ImportEnvironment(env, this);
    }
}

finbl
clbss ImportEnvironment extends Environment {
    Imports imports;

    ImportEnvironment(Environment env, Imports imports) {
        super(env, env.getSource());
        this.imports = imports;
    }

    public Identifier resolve(Identifier nm) throws ClbssNotFound {
        return imports.resolve(this, nm);
    }

    public Imports getImports() {
        return imports;
    }
}
