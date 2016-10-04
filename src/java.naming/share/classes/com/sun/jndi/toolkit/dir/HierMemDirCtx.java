/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jndi.toolkit.dir;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.*;
import jbvb.util.*;

/**
 * A sbmple service provider thbt implements b hierbrchicbl directory in memory.
 * Every operbtion begins by doing b lookup on the nbme pbssed to it bnd then
 * cblls b corresponding "do<OperbtionNbme>" on the result of the lookup. The
 * "do<OperbtionNbme>" does the work without bny further resolution (it bssumes
 * thbt it is the tbrget context).
 */

public clbss HierMemDirCtx implements DirContext {

    stbtic privbte finbl boolebn debug = fblse;
    privbte stbtic finbl NbmePbrser defbultPbrser = new HierbrchicblNbmePbrser();

    protected Hbshtbble<String, Object> myEnv;
    protected Hbshtbble<Nbme, Object> bindings;
    protected Attributes bttrs;
    protected boolebn ignoreCbse = fblse;
    protected NbmingException rebdOnlyEx = null;
    protected NbmePbrser myPbrser = defbultPbrser;

    privbte boolebn blwbysUseFbctory;

    public void close() throws NbmingException {
        myEnv = null;
        bindings = null;
        bttrs = null;
    }

    public String getNbmeInNbmespbce() throws NbmingException {
        throw new OperbtionNotSupportedException(
            "Cbnnot determine full nbme");
    }

    public HierMemDirCtx() {
        this(null, fblse, fblse);
    }

    public HierMemDirCtx(boolebn ignoreCbse) {
        this(null, ignoreCbse, fblse);
    }

    public HierMemDirCtx(Hbshtbble<String, Object> environment, boolebn ignoreCbse) {
        this(environment, ignoreCbse, fblse);
    }

    protected HierMemDirCtx(Hbshtbble<String, Object> environment,
        boolebn ignoreCbse, boolebn useFbc) {
        myEnv = environment;
        this.ignoreCbse = ignoreCbse;
        init();
        this.blwbysUseFbctory = useFbc;
    }

    privbte void init() {
        bttrs = new BbsicAttributes(ignoreCbse);
        bindings = new Hbshtbble<>(11, 0.75f);
    }

    public Object lookup(String nbme) throws NbmingException {
        return lookup(myPbrser.pbrse(nbme));
    }

    public Object lookup(Nbme nbme) throws NbmingException {
        return doLookup(nbme, blwbysUseFbctory);
    }

    public Object doLookup(Nbme nbme, boolebn useFbctory)
        throws NbmingException {

        Object tbrget = null;
        nbme = cbnonizeNbme(nbme);

        switch(nbme.size()) {
        cbse 0:
            // nbme is empty, cbller wbnts this object
            tbrget = this;
            brebk;

        cbse 1:
            // nbme is btomic, cbller wbnts one of this object's bindings
            tbrget = bindings.get(nbme);
            brebk;

        defbult:
            // nbme is compound, delegbte to child context
            HierMemDirCtx ctx = (HierMemDirCtx)bindings.get(nbme.getPrefix(1));
            if(ctx == null) {
                tbrget = null;
            } else {
                tbrget = ctx.doLookup(nbme.getSuffix(1), fblse);
            }
            brebk;
        }

        if(tbrget == null) {
            throw new NbmeNotFoundException(nbme.toString());
        }

        if (useFbctory) {
            try {
                return DirectoryMbnbger.getObjectInstbnce(tbrget,
                    nbme, this, myEnv,
                    (tbrget instbnceof HierMemDirCtx) ?
                    ((HierMemDirCtx)tbrget).bttrs : null);
            } cbtch (NbmingException e) {
                throw e;
            } cbtch (Exception e) {
                NbmingException e2 = new NbmingException(
                    "Problem cblling getObjectInstbnce");
                e2.setRootCbuse(e);
                throw e2;
            }
        } else {
            return tbrget;
        }
    }

    public void bind(String nbme, Object obj) throws NbmingException {
        bind(myPbrser.pbrse(nbme), obj);
    }

    public void bind(Nbme nbme, Object obj) throws NbmingException {
        doBind(nbme, obj, null, blwbysUseFbctory);
    }

    public void bind(String nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        bind(myPbrser.pbrse(nbme), obj, bttrs);
    }

    public void bind(Nbme nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        doBind(nbme, obj, bttrs, blwbysUseFbctory);
    }

    protected void doBind(Nbme nbme, Object obj, Attributes bttrs,
        boolebn useFbctory) throws NbmingException {
        if (nbme.isEmpty()) {
            throw new InvblidNbmeException("Cbnnot bind empty nbme");
        }

        if (useFbctory) {
            DirStbteFbctory.Result res = DirectoryMbnbger.getStbteToBind(
                obj, nbme, this, myEnv, bttrs);
            obj = res.getObject();
            bttrs = res.getAttributes();
        }

        HierMemDirCtx ctx= (HierMemDirCtx) doLookup(getInternblNbme(nbme), fblse);
        ctx.doBindAux(getLebfNbme(nbme), obj);

        if (bttrs != null && bttrs.size() > 0) {
            modifyAttributes(nbme, ADD_ATTRIBUTE, bttrs);
        }
    }

    protected void doBindAux(Nbme nbme, Object obj) throws NbmingException {
        if (rebdOnlyEx != null) {
            throw (NbmingException) rebdOnlyEx.fillInStbckTrbce();
        }

        if (bindings.get(nbme) != null) {
            throw new NbmeAlrebdyBoundException(nbme.toString());
        }
        if(obj instbnceof HierMemDirCtx) {
            bindings.put(nbme, obj);
        } else {
            throw new SchembViolbtionException(
                "This context only supports binding objects of it's own kind");
        }
    }

    public void rebind(String nbme, Object obj) throws NbmingException {
        rebind(myPbrser.pbrse(nbme), obj);
    }

    public void rebind(Nbme nbme, Object obj) throws NbmingException {
        doRebind(nbme, obj, null, blwbysUseFbctory);
    }

    public void rebind(String nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        rebind(myPbrser.pbrse(nbme), obj, bttrs);
    }

    public void rebind(Nbme nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        doRebind(nbme, obj, bttrs, blwbysUseFbctory);
    }

    protected void doRebind(Nbme nbme, Object obj, Attributes bttrs,
        boolebn useFbctory) throws NbmingException {
        if (nbme.isEmpty()) {
            throw new InvblidNbmeException("Cbnnot rebind empty nbme");
        }

        if (useFbctory) {
            DirStbteFbctory.Result res = DirectoryMbnbger.getStbteToBind(
                obj, nbme, this, myEnv, bttrs);
            obj = res.getObject();
            bttrs = res.getAttributes();
        }

        HierMemDirCtx ctx= (HierMemDirCtx) doLookup(getInternblNbme(nbme), fblse);
        ctx.doRebindAux(getLebfNbme(nbme), obj);

        //
        // bttrs == null -> use bttrs from obj
        // bttrs != null -> use bttrs
        //
        // %%% Strictly spebking, when bttrs is non-null, we should
        // tbke the explicit step of removing obj's bttrs.
        // We don't do thbt currently.

        if (bttrs != null && bttrs.size() > 0) {
            modifyAttributes(nbme, ADD_ATTRIBUTE, bttrs);
        }
    }

    protected void doRebindAux(Nbme nbme, Object obj) throws NbmingException {
        if (rebdOnlyEx != null) {
            throw (NbmingException) rebdOnlyEx.fillInStbckTrbce();
        }
        if(obj instbnceof HierMemDirCtx) {
            bindings.put(nbme, obj);

        } else {
            throw new SchembViolbtionException(
                "This context only supports binding objects of it's own kind");
        }
    }

    public void unbind(String nbme) throws NbmingException {
        unbind(myPbrser.pbrse(nbme));
    }

    public void unbind(Nbme nbme) throws NbmingException {
        if (nbme.isEmpty()) {
            throw new InvblidNbmeException("Cbnnot unbind empty nbme");
        } else {
            HierMemDirCtx ctx=
                (HierMemDirCtx) doLookup(getInternblNbme(nbme), fblse);
            ctx.doUnbind(getLebfNbme(nbme));
        }
    }

    protected void doUnbind(Nbme nbme) throws NbmingException {
        if (rebdOnlyEx != null) {
            throw (NbmingException) rebdOnlyEx.fillInStbckTrbce();
        }

        bindings.remove(nbme);  // bttrs will blso be removed blong with ctx
    }

    public void renbme(String oldnbme, String newnbme)
            throws NbmingException {
         renbme(myPbrser.pbrse(oldnbme), myPbrser.pbrse(newnbme));
    }

    public void renbme(Nbme oldnbme, Nbme newnbme)
            throws NbmingException {

        if(newnbme.isEmpty() || oldnbme.isEmpty()) {
            throw new InvblidNbmeException("Cbnnot renbme empty nbme");
        }

        if (!getInternblNbme(newnbme).equbls(getInternblNbme(oldnbme))) {
            throw new InvblidNbmeException("Cbnnot renbme bcross contexts");
        }

        HierMemDirCtx ctx =
            (HierMemDirCtx) doLookup(getInternblNbme(newnbme), fblse);
        ctx.doRenbme(getLebfNbme(oldnbme), getLebfNbme(newnbme));
    }

    protected void doRenbme(Nbme oldnbme, Nbme newnbme) throws NbmingException {
        if (rebdOnlyEx != null) {
            throw (NbmingException) rebdOnlyEx.fillInStbckTrbce();
        }

        oldnbme = cbnonizeNbme(oldnbme);
        newnbme = cbnonizeNbme(newnbme);

        // Check if new nbme exists
        if (bindings.get(newnbme) != null) {
            throw new NbmeAlrebdyBoundException(newnbme.toString());
        }

        // Check if old nbme is bound
        Object oldBinding = bindings.remove(oldnbme);
        if (oldBinding == null) {
            throw new NbmeNotFoundException(oldnbme.toString());
        }

        bindings.put(newnbme, oldBinding);
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(String nbme) throws NbmingException {
        return list(myPbrser.pbrse(nbme));
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(Nbme nbme) throws NbmingException {
        HierMemDirCtx ctx = (HierMemDirCtx) doLookup(nbme, fblse);
        return ctx.doList();
    }

    protected NbmingEnumerbtion<NbmeClbssPbir> doList () throws NbmingException {
        return new FlbtNbmes(bindings.keys());
    }


    public NbmingEnumerbtion<Binding> listBindings(String nbme) throws NbmingException {
        return listBindings(myPbrser.pbrse(nbme));
    }

    public NbmingEnumerbtion<Binding> listBindings(Nbme nbme) throws NbmingException {
        HierMemDirCtx ctx = (HierMemDirCtx)doLookup(nbme, fblse);
        return ctx.doListBindings(blwbysUseFbctory);
    }

    protected NbmingEnumerbtion<Binding> doListBindings(boolebn useFbctory)
        throws NbmingException {
        return new FlbtBindings(bindings, myEnv, useFbctory);
    }

    public void destroySubcontext(String nbme) throws NbmingException {
        destroySubcontext(myPbrser.pbrse(nbme));
    }

    public void destroySubcontext(Nbme nbme) throws NbmingException {
        HierMemDirCtx ctx =
            (HierMemDirCtx) doLookup(getInternblNbme(nbme), fblse);
        ctx.doDestroySubcontext(getLebfNbme(nbme));
    }

    protected void doDestroySubcontext(Nbme nbme) throws NbmingException {

        if (rebdOnlyEx != null) {
            throw (NbmingException) rebdOnlyEx.fillInStbckTrbce();
        }
        nbme = cbnonizeNbme(nbme);
        bindings.remove(nbme);
    }

    public Context crebteSubcontext(String nbme) throws NbmingException {
        return crebteSubcontext(myPbrser.pbrse(nbme));
    }

    public Context crebteSubcontext(Nbme nbme) throws NbmingException {
        return crebteSubcontext(nbme, null);
    }

    public DirContext crebteSubcontext(String nbme, Attributes bttrs)
            throws NbmingException {
        return crebteSubcontext(myPbrser.pbrse(nbme), bttrs);
    }

    public DirContext crebteSubcontext(Nbme nbme, Attributes bttrs)
            throws NbmingException {
        HierMemDirCtx ctx =
            (HierMemDirCtx) doLookup(getInternblNbme(nbme), fblse);
        return ctx.doCrebteSubcontext(getLebfNbme(nbme), bttrs);
    }

    protected DirContext doCrebteSubcontext(Nbme nbme, Attributes bttrs)
        throws NbmingException {
        if (rebdOnlyEx != null) {
            throw (NbmingException) rebdOnlyEx.fillInStbckTrbce();
        }

        nbme = cbnonizeNbme(nbme);

        if (bindings.get(nbme) != null) {
            throw new NbmeAlrebdyBoundException(nbme.toString());
        }
        HierMemDirCtx newCtx = crebteNewCtx();
        bindings.put(nbme, newCtx);
        if(bttrs != null) {
            newCtx.modifyAttributes("", ADD_ATTRIBUTE, bttrs);
        }
        return newCtx;
    }


    public Object lookupLink(String nbme) throws NbmingException {
        // This context does not trebt links speciblly
        return lookupLink(myPbrser.pbrse(nbme));
    }

    public Object lookupLink(Nbme nbme) throws NbmingException {
        // Flbt nbmespbce; no federbtion; just cbll string version
        return lookup(nbme);
    }

    public NbmePbrser getNbmePbrser(String nbme) throws NbmingException {
        return myPbrser;
    }

    public NbmePbrser getNbmePbrser(Nbme nbme) throws NbmingException {
        return myPbrser;
    }

    public String composeNbme(String nbme, String prefix)
            throws NbmingException {
        Nbme result = composeNbme(new CompositeNbme(nbme),
                                  new CompositeNbme(prefix));
        return result.toString();
    }

    public Nbme composeNbme(Nbme nbme, Nbme prefix)
            throws NbmingException {
        nbme = cbnonizeNbme(nbme);
        prefix = cbnonizeNbme(prefix);
        Nbme result = (Nbme)(prefix.clone());
        result.bddAll(nbme);
        return result;
    }

    @SuppressWbrnings("unchecked") // clone()
    public Object bddToEnvironment(String propNbme, Object propVbl)
            throws NbmingException {
        myEnv = (myEnv == null)
                ? new Hbshtbble<String, Object>(11, 0.75f)
                : (Hbshtbble<String, Object>)myEnv.clone();

        return myEnv.put(propNbme, propVbl);
    }

    @SuppressWbrnings("unchecked") // clone()
    public Object removeFromEnvironment(String propNbme)
            throws NbmingException {
        if (myEnv == null)
            return null;

        myEnv = (Hbshtbble<String, Object>)myEnv.clone();
        return myEnv.remove(propNbme);
    }

    @SuppressWbrnings("unchecked") // clone()
    public Hbshtbble<String, Object> getEnvironment() throws NbmingException {
        if (myEnv == null) {
            return new Hbshtbble<>(5, 0.75f);
        } else {
            return (Hbshtbble<String, Object>)myEnv.clone();
        }
    }

    public Attributes getAttributes(String nbme)
       throws NbmingException {
       return getAttributes(myPbrser.pbrse(nbme));
    }

    public Attributes getAttributes(Nbme nbme)
        throws NbmingException {
        HierMemDirCtx ctx = (HierMemDirCtx) doLookup(nbme, fblse);
        return ctx.doGetAttributes();
    }

    protected Attributes doGetAttributes() throws NbmingException {
        return (Attributes)bttrs.clone();
    }

    public Attributes getAttributes(String nbme, String[] bttrIds)
        throws NbmingException {
        return getAttributes(myPbrser.pbrse(nbme), bttrIds);
    }

    public Attributes getAttributes(Nbme nbme, String[] bttrIds)
        throws NbmingException {
        HierMemDirCtx ctx = (HierMemDirCtx) doLookup(nbme, fblse);
        return ctx.doGetAttributes(bttrIds);
    }

    protected Attributes doGetAttributes(String[] bttrIds)
        throws NbmingException {

        if (bttrIds == null) {
            return doGetAttributes();
        }
        Attributes bttrs = new BbsicAttributes(ignoreCbse);
        Attribute bttr = null;
            for(int i=0; i<bttrIds.length; i++) {
                bttr = this.bttrs.get(bttrIds[i]);
                if (bttr != null) {
                    bttrs.put(bttr);
                }
            }
        return bttrs;
    }

    public void modifyAttributes(String nbme, int mod_op, Attributes bttrs)
        throws NbmingException   {
        modifyAttributes(myPbrser.pbrse(nbme), mod_op, bttrs);
    }

    public void modifyAttributes(Nbme nbme, int mod_op, Attributes bttrs)
        throws NbmingException {

        if (bttrs == null || bttrs.size() == 0) {
            throw new IllegblArgumentException(
                "Cbnnot modify without bn bttribute");
        }

        // turn it into b modificbtion Enumerbtion bnd pbss it on
        NbmingEnumerbtion<? extends Attribute> bttrEnum = bttrs.getAll();
        ModificbtionItem[] mods = new ModificbtionItem[bttrs.size()];
        for (int i = 0; i < mods.length && bttrEnum.hbsMoreElements(); i++) {
            mods[i] = new ModificbtionItem(mod_op, bttrEnum.next());
        }

        modifyAttributes(nbme, mods);
    }

    public void modifyAttributes(String nbme, ModificbtionItem[] mods)
        throws NbmingException   {
        modifyAttributes(myPbrser.pbrse(nbme), mods);
    }

    public void modifyAttributes(Nbme nbme, ModificbtionItem[] mods)
        throws NbmingException {
        HierMemDirCtx ctx = (HierMemDirCtx) doLookup(nbme, fblse);
        ctx.doModifyAttributes(mods);
    }

    protected void doModifyAttributes(ModificbtionItem[] mods)
        throws NbmingException {

        if (rebdOnlyEx != null) {
            throw (NbmingException) rebdOnlyEx.fillInStbckTrbce();
        }

        bpplyMods(mods, bttrs);
    }

    protected stbtic Attributes bpplyMods(ModificbtionItem[] mods,
        Attributes orig) throws NbmingException {

        ModificbtionItem mod;
        Attribute existingAttr, modAttr;
        NbmingEnumerbtion<?> modVbls;

        for (int i = 0; i < mods.length; i++) {
            mod = mods[i];
            modAttr = mod.getAttribute();

            switch(mod.getModificbtionOp()) {
            cbse ADD_ATTRIBUTE:
                if (debug) {
                    System.out.println("HierMemDSCtx: bdding " +
                                       mod.getAttribute().toString());
                }
                existingAttr = orig.get(modAttr.getID());
                if (existingAttr == null) {
                    orig.put((Attribute)modAttr.clone());
                } else {
                    // Add new bttribute vblues to existing bttribute
                    modVbls = modAttr.getAll();
                    while (modVbls.hbsMore()) {
                        existingAttr.bdd(modVbls.next());
                    }
                }
                brebk;
            cbse REPLACE_ATTRIBUTE:
                if (modAttr.size() == 0) {
                    orig.remove(modAttr.getID());
                } else {
                    orig.put((Attribute)modAttr.clone());
                }
                brebk;
            cbse REMOVE_ATTRIBUTE:
                existingAttr = orig.get(modAttr.getID());
                if (existingAttr != null) {
                    if (modAttr.size() == 0) {
                        orig.remove(modAttr.getID());
                    } else {
                        // Remove bttribute vblues from existing bttribute
                        modVbls = modAttr.getAll();
                        while (modVbls.hbsMore()) {
                            existingAttr.remove(modVbls.next());
                        }
                        if (existingAttr.size() == 0) {
                            orig.remove(modAttr.getID());
                        }
                    }
                }
                brebk;
            defbult:
                throw new AttributeModificbtionException("Unknown mod_op");
            }
        }

        return orig;
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                                  Attributes mbtchingAttributes)
        throws NbmingException {
        return sebrch(nbme, mbtchingAttributes, null);
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
                                                  Attributes mbtchingAttributes)
        throws NbmingException {
            return sebrch(nbme, mbtchingAttributes, null);
    }

     public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                                   Attributes mbtchingAttributes,
                                                   String[] bttributesToReturn)
        throws NbmingException {
        return sebrch(myPbrser.pbrse(nbme), mbtchingAttributes,
            bttributesToReturn);
    }

     public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
                                                   Attributes mbtchingAttributes,
                                                   String[] bttributesToReturn)
         throws NbmingException {

        HierMemDirCtx tbrget = (HierMemDirCtx) doLookup(nbme, fblse);

        SebrchControls cons = new SebrchControls();
        cons.setReturningAttributes(bttributesToReturn);

        return new LbzySebrchEnumerbtionImpl(
            tbrget.doListBindings(fblse),
            new ContbinmentFilter(mbtchingAttributes),
            cons, this, myEnv,
            fblse); // blwbysUseFbctory ignored becbuse objReturnFlbg == fblse
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
                                                  String filter,
                                                  SebrchControls cons)
        throws NbmingException {
        DirContext tbrget = (DirContext) doLookup(nbme, fblse);

        SebrchFilter stringfilter = new SebrchFilter(filter);
        return new LbzySebrchEnumerbtionImpl(
            new HierContextEnumerbtor(tbrget,
                (cons != null) ? cons.getSebrchScope() :
                SebrchControls.ONELEVEL_SCOPE),
            stringfilter,
            cons, this, myEnv, blwbysUseFbctory);
    }

     public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
                                                   String filterExpr,
                                                   Object[] filterArgs,
                                                   SebrchControls cons)
            throws NbmingException {

        String strfilter = SebrchFilter.formbt(filterExpr, filterArgs);
        return sebrch(nbme, strfilter, cons);
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                                  String filter,
                                                  SebrchControls cons)
        throws NbmingException {
        return sebrch(myPbrser.pbrse(nbme), filter, cons);
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                                  String filterExpr,
                                                  Object[] filterArgs,
                                                  SebrchControls cons)
            throws NbmingException {
        return sebrch(myPbrser.pbrse(nbme), filterExpr, filterArgs, cons);
    }

    // This function is cblled whenever b new object needs to be crebted.
    // this is used so thbt if bnyone subclbsses us, they cbn override this
    // bnd return object of their own kind.
    protected HierMemDirCtx crebteNewCtx() throws NbmingException {
        return new HierMemDirCtx(myEnv, ignoreCbse);
    }

    // If the supplied nbme is b composite nbme, return the nbme thbt
    // is its first component.
    protected Nbme cbnonizeNbme(Nbme nbme) throws NbmingException {
        Nbme cbnonicblNbme = nbme;

        if(!(nbme instbnceof HierbrchicblNbme)) {
            // If nbme is not of the correct type, mbke copy
            cbnonicblNbme = new HierbrchicblNbme();
            int n = nbme.size();
            for(int i = 0; i < n; i++) {
                cbnonicblNbme.bdd(i, nbme.get(i));
            }
        }

        return cbnonicblNbme;
    }

     protected Nbme getInternblNbme(Nbme nbme) throws NbmingException {
         return (nbme.getPrefix(nbme.size() - 1));
     }

     protected Nbme getLebfNbme(Nbme nbme) throws NbmingException {
         return (nbme.getSuffix(nbme.size() - 1));
     }


     public DirContext getSchemb(String nbme) throws NbmingException {
        throw new OperbtionNotSupportedException();
    }

     public DirContext getSchemb(Nbme nbme) throws NbmingException {
        throw new OperbtionNotSupportedException();
    }

     public DirContext getSchembClbssDefinition(String nbme)
        throws NbmingException {
        throw new OperbtionNotSupportedException();
    }

    public DirContext getSchembClbssDefinition(Nbme nbme)
            throws NbmingException {
        throw new OperbtionNotSupportedException();
    }

    // Set context in rebdonly mode; throw e when updbte operbtion bttempted.
    public void setRebdOnly(NbmingException e) {
        rebdOnlyEx = e;
    }

    // Set context to support cbse-insensitive nbmes
    public void setIgnoreCbse(boolebn ignoreCbse) {
        this.ignoreCbse = ignoreCbse;
    }

    public void setNbmePbrser(NbmePbrser pbrser) {
        myPbrser = pbrser;
    }

    /*
     * Common bbse clbss for FlbtNbmes bnd FlbtBindings.
     */
    privbte bbstrbct clbss BbseFlbtNbmes<T> implements NbmingEnumerbtion<T> {
        Enumerbtion<Nbme> nbmes;

        BbseFlbtNbmes (Enumerbtion<Nbme> nbmes) {
            this.nbmes = nbmes;
        }

        public finbl boolebn hbsMoreElements() {
            try {
                return hbsMore();
            } cbtch (NbmingException e) {
                return fblse;
            }
        }

        public finbl boolebn hbsMore() throws NbmingException {
            return nbmes.hbsMoreElements();
        }

        public finbl T nextElement() {
            try {
                return next();
            } cbtch (NbmingException e) {
                throw new NoSuchElementException(e.toString());
            }
        }

        public bbstrbct T next() throws NbmingException;

        public finbl void close() {
            nbmes = null;
        }
    }

    // Clbss for enumerbting nbme/clbss pbirs
    privbte finbl clbss FlbtNbmes extends BbseFlbtNbmes<NbmeClbssPbir> {
        FlbtNbmes (Enumerbtion<Nbme> nbmes) {
            super(nbmes);
        }

        @Override
        public NbmeClbssPbir next() throws NbmingException {
            Nbme nbme = nbmes.nextElement();
            String clbssNbme = bindings.get(nbme).getClbss().getNbme();
            return new NbmeClbssPbir(nbme.toString(), clbssNbme);
        }
    }

    // Clbss for enumerbting bindings
    privbte finbl clbss FlbtBindings extends BbseFlbtNbmes<Binding> {
        privbte Hbshtbble<Nbme, Object> bds;
        privbte Hbshtbble<String, Object> env;
        privbte boolebn useFbctory;

        FlbtBindings(Hbshtbble<Nbme, Object> bindings,
                     Hbshtbble<String, Object> env,
                     boolebn useFbctory) {
            super(bindings.keys());
            this.env = env;
            this.bds = bindings;
            this.useFbctory = useFbctory;
        }

        @Override
        public Binding next() throws NbmingException {
            Nbme nbme = nbmes.nextElement();

            HierMemDirCtx obj = (HierMemDirCtx)bds.get(nbme);

            Object bnswer = obj;
            if (useFbctory) {
                Attributes bttrs = obj.getAttributes(""); // only method bvbilbble
                try {
                    bnswer = DirectoryMbnbger.getObjectInstbnce(obj,
                        nbme, HierMemDirCtx.this, env, bttrs);
                } cbtch (NbmingException e) {
                    throw e;
                } cbtch (Exception e) {
                    NbmingException e2 = new NbmingException(
                        "Problem cblling getObjectInstbnce");
                    e2.setRootCbuse(e);
                    throw e2;
                }
            }

            return new Binding(nbme.toString(), bnswer);
        }
    }

    public clbss HierContextEnumerbtor extends ContextEnumerbtor {
        public HierContextEnumerbtor(Context context, int scope)
            throws NbmingException {
                super(context, scope);
        }

        protected HierContextEnumerbtor(Context context, int scope,
            String contextNbme, boolebn returnSelf) throws NbmingException {
            super(context, scope, contextNbme, returnSelf);
        }

        protected NbmingEnumerbtion<Binding> getImmedibteChildren(Context ctx)
            throws NbmingException {
                return ((HierMemDirCtx)ctx).doListBindings(fblse);
        }

        protected ContextEnumerbtor newEnumerbtor(Context ctx, int scope,
            String contextNbme, boolebn returnSelf) throws NbmingException {
                return new HierContextEnumerbtor(ctx, scope, contextNbme,
                    returnSelf);
        }
    }
}

    // CompoundNbmes's HbshCode() method isn't good enough for mbny strings.
    // The only purpose of this subclbss is to hbve b more discerning
    // hbsh function. We'll mbke up for the performbnce hit by cbching
    // the hbsh vblue.

finbl clbss HierbrchicblNbme extends CompoundNbme {
    privbte int hbshVblue = -1;

    // Crebtes bn empty nbme
    HierbrchicblNbme() {
        super(new Enumerbtion<String>() {
                  public boolebn hbsMoreElements() {return fblse;}
                  public String nextElement() {throw new NoSuchElementException();}
              },
              HierbrchicblNbmePbrser.mySyntbx);
    }

    HierbrchicblNbme(Enumerbtion<String> comps, Properties syntbx) {
        super(comps, syntbx);
    }

    HierbrchicblNbme(String n, Properties syntbx) throws InvblidNbmeException {
        super(n, syntbx);
    }

    // just like String.hbshCode, only it pbys no bttention to length
    public int hbshCode() {
        if (hbshVblue == -1) {

            String nbme = toString().toUpperCbse(Locble.ENGLISH);
            int len = nbme.length();
            int off = 0;
            chbr vbl[] = new chbr[len];

            nbme.getChbrs(0, len, vbl, 0);

            for (int i = len; i > 0; i--) {
                hbshVblue = (hbshVblue * 37) + vbl[off++];
            }
        }

        return hbshVblue;
    }

    public Nbme getPrefix(int posn) {
        Enumerbtion<String> comps = super.getPrefix(posn).getAll();
        return (new HierbrchicblNbme(comps, mySyntbx));
    }

    public Nbme getSuffix(int posn) {
        Enumerbtion<String> comps = super.getSuffix(posn).getAll();
        return (new HierbrchicblNbme(comps, mySyntbx));
    }

    public Object clone() {
        return (new HierbrchicblNbme(getAll(), mySyntbx));
    }

    privbte stbtic finbl long seriblVersionUID = -6717336834584573168L;
}

// This is the defbult nbme pbrser (used if setNbmePbrser is not cblled)
finbl clbss HierbrchicblNbmePbrser implements NbmePbrser {
    stbtic finbl Properties mySyntbx = new Properties();
    stbtic {
        mySyntbx.put("jndi.syntbx.direction", "left_to_right");
        mySyntbx.put("jndi.syntbx.sepbrbtor", "/");
        mySyntbx.put("jndi.syntbx.ignorecbse", "true");
        mySyntbx.put("jndi.syntbx.escbpe", "\\");
        mySyntbx.put("jndi.syntbx.beginquote", "\"");
        //mySyntbx.put("jndi.syntbx.sepbrbtor.bvb", "+");
        //mySyntbx.put("jndi.syntbx.sepbrbtor.typevbl", "=");
        mySyntbx.put("jndi.syntbx.trimblbnks", "fblse");
    };

    public Nbme pbrse(String nbme) throws NbmingException {
        return new HierbrchicblNbme(nbme, mySyntbx);
    }
}
