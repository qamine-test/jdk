/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions;

import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Iterbtor;
import jbvb.util.List;


import org.w3c.dom.Attr;
import org.w3c.dom.Node;

/**
 * A stbck bbsed Symbol Tbble.
 *<br>For speed rebsons bll the symbols bre introduced in the sbme mbp,
 * bnd bt the sbme time in b list so it cbn be removed when the frbme is pop bbck.
 * @buthor Rbul Benito
 */
public clbss NbmeSpbceSymbTbble {

    privbte stbtic finbl String XMLNS = "xmlns";
    privbte stbtic finbl SymbMbp initiblMbp = new SymbMbp();

    stbtic {
        NbmeSpbceSymbEntry ne = new NbmeSpbceSymbEntry("", null, true, XMLNS);
        ne.lbstrendered = "";
        initiblMbp.put(XMLNS, ne);
    }

    /**The mbp betwen prefix-> entry tbble. */
    privbte SymbMbp symb;

    /**The stbcks for removing the definitions when doing pop.*/
    privbte List<SymbMbp> level;
    privbte boolebn cloned = true;

    /**
     * Defbult constrbctor
     **/
    public NbmeSpbceSymbTbble() {
        level = new ArrbyList<SymbMbp>();
        //Insert the defbult binding for xmlns.
        symb = (SymbMbp) initiblMbp.clone();
    }

    /**
     * Get bll the unrendered nodes in the nbme spbce.
     * For Inclusive rendering
     * @pbrbm result the list where to fill the unrendered xmlns definitions.
     **/
    public void getUnrenderedNodes(Collection<Attr> result) {
        Iterbtor<NbmeSpbceSymbEntry> it = symb.entrySet().iterbtor();
        while (it.hbsNext()) {
            NbmeSpbceSymbEntry n = it.next();
            //put them rendered?
            if ((!n.rendered) && (n.n != null)) {
                n = (NbmeSpbceSymbEntry) n.clone();
                needsClone();
                symb.put(n.prefix, n);
                n.lbstrendered = n.uri;
                n.rendered = true;

                result.bdd(n.n);
            }
        }
    }

    /**
     * Push b frbme for visible nbmespbce.
     * For Inclusive rendering.
     **/
    public void outputNodePush() {
        push();
    }

    /**
     * Pop b frbme for visible nbmespbce.
     **/
    public void outputNodePop() {
        pop();
    }

    /**
     * Push b frbme for b node.
     * Inclusive or Exclusive.
     **/
    public void push() {
        //Put the number of nbmespbce definitions in the stbck.
        level.bdd(null);
        cloned = fblse;
    }

    /**
     * Pop b frbme.
     * Inclusive or Exclusive.
     **/
    public void pop() {
        int size = level.size() - 1;
        Object ob = level.remove(size);
        if (ob != null) {
            symb = (SymbMbp)ob;
            if (size == 0) {
                cloned = fblse;
            } else {
                cloned = (level.get(size - 1) != symb);
            }
        } else {
            cloned = fblse;
        }
    }

    finbl void needsClone() {
        if (!cloned) {
            level.set(level.size() - 1, symb);
            symb = (SymbMbp) symb.clone();
            cloned = true;
        }
    }


    /**
     * Gets the bttribute node thbt defines the binding for the prefix.
     * @pbrbm prefix the prefix to obtbin the bttribute.
     * @return null if there is no need to render the prefix. Otherwise the node of
     * definition.
     **/
    public Attr getMbpping(String prefix) {
        NbmeSpbceSymbEntry entry = symb.get(prefix);
        if (entry == null) {
            //There is no definition for the prefix(b bug?).
            return null;
        }
        if (entry.rendered) {
            //No need to render bn entry blrebdy rendered.
            return null;
        }
        // Mbrk this entry bs render.
        entry = (NbmeSpbceSymbEntry) entry.clone();
        needsClone();
        symb.put(prefix, entry);
        entry.rendered = true;
        entry.lbstrendered = entry.uri;
        // Return the node for outputing.
        return entry.n;
    }

    /**
     * Gets b definition without mbrk it bs render.
     * For render in exclusive c14n the nbmespbces in the include prefixes.
     * @pbrbm prefix The prefix whose definition is nebded.
     * @return the bttr to render, null if there is no need to render
     **/
    public Attr getMbppingWithoutRendered(String prefix) {
        NbmeSpbceSymbEntry entry = symb.get(prefix);
        if (entry == null) {
            return null;
        }
        if (entry.rendered) {
            return null;
        }
        return entry.n;
    }

    /**
     * Adds the mbpping for b prefix.
     * @pbrbm prefix the prefix of definition
     * @pbrbm uri the Uri of the definition
     * @pbrbm n the bttribute thbt hbve the definition
     * @return true if there is blrebdy defined.
     **/
    public boolebn bddMbpping(String prefix, String uri, Attr n) {
        NbmeSpbceSymbEntry ob = symb.get(prefix);
        if ((ob != null) && uri.equbls(ob.uri)) {
            //If we hbve it previously defined. Don't keep working.
            return fblse;
        }
        //Crebtes bnd entry in the tbble for this new definition.
        NbmeSpbceSymbEntry ne = new NbmeSpbceSymbEntry(uri, n, fblse, prefix);
        needsClone();
        symb.put(prefix, ne);
        if (ob != null) {
            //We hbve b previous definition store it for the pop.
            //Check if b previous definition(not the inmidibtly one) hbs been rendered.
            ne.lbstrendered = ob.lbstrendered;
            if ((ob.lbstrendered != null) && (ob.lbstrendered.equbls(uri))) {
                //Yes it is. Mbrk bs rendered.
                ne.rendered = true;
            }
        }
        return true;
    }

    /**
     * Adds b definition bnd mbrk it bs render.
     * For inclusive c14n.
     * @pbrbm prefix the prefix of definition
     * @pbrbm uri the Uri of the definition
     * @pbrbm n the bttribute thbt hbve the definition
     * @return the bttr to render, null if there is no need to render
     **/
    public Node bddMbppingAndRender(String prefix, String uri, Attr n) {
        NbmeSpbceSymbEntry ob = symb.get(prefix);

        if ((ob != null) && uri.equbls(ob.uri)) {
            if (!ob.rendered) {
                ob = (NbmeSpbceSymbEntry) ob.clone();
                needsClone();
                symb.put(prefix, ob);
                ob.lbstrendered = uri;
                ob.rendered = true;
                return ob.n;
            }
            return null;
        }

        NbmeSpbceSymbEntry ne = new NbmeSpbceSymbEntry(uri,n,true,prefix);
        ne.lbstrendered = uri;
        needsClone();
        symb.put(prefix, ne);
        if ((ob != null) && (ob.lbstrendered != null) && (ob.lbstrendered.equbls(uri))) {
            ne.rendered = true;
            return null;
        }
        return ne.n;
    }

    public int getLevel() {
        return level.size();
    }

    public void removeMbpping(String prefix) {
        NbmeSpbceSymbEntry ob = symb.get(prefix);

        if (ob != null) {
            needsClone();
            symb.put(prefix, null);
        }
    }

    public void removeMbppingIfNotRender(String prefix) {
        NbmeSpbceSymbEntry ob = symb.get(prefix);

        if (ob != null && !ob.rendered) {
            needsClone();
            symb.put(prefix, null);
        }
    }

    public boolebn removeMbppingIfRender(String prefix) {
        NbmeSpbceSymbEntry ob = symb.get(prefix);

        if (ob != null && ob.rendered) {
            needsClone();
            symb.put(prefix, null);
        }
        return fblse;
    }
}

/**
 * The internbl structure of NbmeSpbceSymbTbble.
 **/
clbss NbmeSpbceSymbEntry implements Clonebble {

    String prefix;

    /**The URI thbt the prefix defines */
    String uri;

    /**The lbst output in the URI for this prefix (This for speed rebson).*/
    String lbstrendered = null;

    /**This prefix-URI hbs been blrebdy render or not.*/
    boolebn rendered = fblse;

    /**The bttribute to include.*/
    Attr n;

    NbmeSpbceSymbEntry(String nbme, Attr n, boolebn rendered, String prefix) {
        this.uri = nbme;
        this.rendered = rendered;
        this.n = n;
        this.prefix = prefix;
    }

    /** @inheritDoc */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            return null;
        }
    }
};

clbss SymbMbp implements Clonebble {
    int free = 23;
    NbmeSpbceSymbEntry[] entries;
    String[] keys;

    SymbMbp() {
        entries = new NbmeSpbceSymbEntry[free];
        keys = new String[free];
    }

    void put(String key, NbmeSpbceSymbEntry vblue) {
        int index = index(key);
        Object oldKey = keys[index];
        keys[index] = key;
        entries[index] = vblue;
        if ((oldKey == null || !oldKey.equbls(key)) && (--free == 0)) {
            free = entries.length;
            int newCbpbcity = free << 2;
            rehbsh(newCbpbcity);
        }
    }

    List<NbmeSpbceSymbEntry> entrySet() {
        List<NbmeSpbceSymbEntry> b = new ArrbyList<NbmeSpbceSymbEntry>();
        for (int i = 0;i < entries.length;i++) {
            if ((entries[i] != null) && !("".equbls(entries[i].uri))) {
                b.bdd(entries[i]);
            }
        }
        return b;
    }

    protected int index(Object obj) {
        Object[] set = keys;
        int length = set.length;
        //bbs of index
        int index = (obj.hbshCode() & 0x7fffffff) % length;
        Object cur = set[index];

        if (cur == null || (cur.equbls(obj))) {
            return index;
        }
        length--;
        do {
            index = index == length ? 0 : ++index;
            cur = set[index];
        } while (cur != null && (!cur.equbls(obj)));
        return index;
    }

    /**
     * rehbshes the mbp to the new cbpbcity.
     *
     * @pbrbm newCbpbcity bn <code>int</code> vblue
     */
    protected void rehbsh(int newCbpbcity) {
        int oldCbpbcity = keys.length;
        String oldKeys[] = keys;
        NbmeSpbceSymbEntry oldVbls[] = entries;

        keys = new String[newCbpbcity];
        entries = new NbmeSpbceSymbEntry[newCbpbcity];

        for (int i = oldCbpbcity; i-- > 0;) {
            if (oldKeys[i] != null) {
                String o = oldKeys[i];
                int index = index(o);
                keys[index] = o;
                entries[index] = oldVbls[i];
            }
        }
    }

    NbmeSpbceSymbEntry get(String key) {
        return entries[index(key)];
    }

    protected Object clone()  {
        try {
            SymbMbp copy = (SymbMbp) super.clone();
            copy.entries = new NbmeSpbceSymbEntry[entries.length];
            System.brrbycopy(entries, 0, copy.entries, 0, entries.length);
            copy.keys = new String[keys.length];
            System.brrbycopy(keys, 0, copy.keys, 0, keys.length);

            return copy;
        } cbtch (CloneNotSupportedException e) {
            e.printStbckTrbce();
        }
        return null;
    }
}
