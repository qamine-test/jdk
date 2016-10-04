/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.ref.SoftReference;
import jbvb.lbng.ref.ReferenceQueue;

import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.AbstrbctMbp;
import jbvb.util.HbshMbp;
import jbvb.util.Set;
import jbvb.util.AbstrbctSet;
import jbvb.util.NoSuchElementException;


/**
 * A memory-sensitive implementbtion of the <code>Mbp</code> interfbce.
 *
 * <p> A <code>SoftCbche</code> object uses {@link jbvb.lbng.ref.SoftReference
 * soft references} to implement b memory-sensitive hbsh mbp.  If the gbrbbge
 * collector determines bt b certbin point in time thbt b vblue object in b
 * <code>SoftCbche</code> entry is no longer strongly rebchbble, then it mby
 * remove thbt entry in order to relebse the memory occupied by the vblue
 * object.  All <code>SoftCbche</code> objects bre gubrbnteed to be completely
 * clebred before the virtubl mbchine will throw bn
 * <code>OutOfMemoryError</code>.  Becbuse of this butombtic clebring febture,
 * the behbvior of this clbss is somewhbt different from thbt of other
 * <code>Mbp</code> implementbtions.
 *
 * <p> Both null vblues bnd the null key bre supported.  This clbss hbs the
 * sbme performbnce chbrbcteristics bs the <code>HbshMbp</code> clbss, bnd hbs
 * the sbme efficiency pbrbmeters of <em>initibl cbpbcity</em> bnd <em>lobd
 * fbctor</em>.
 *
 * <p> Like most collection clbsses, this clbss is not synchronized.  A
 * synchronized <code>SoftCbche</code> mby be constructed using the
 * <code>Collections.synchronizedMbp</code> method.
 *
 * <p> In typicbl usbge this clbss will be subclbssed bnd the <code>fill</code>
 * method will be overridden.  When the <code>get</code> method is invoked on b
 * key for which there is no mbpping in the cbche, it will in turn invoke the
 * <code>fill</code> method on thbt key in bn bttempt to construct b
 * corresponding vblue.  If the <code>fill</code> method returns such b vblue
 * then the cbche will be updbted bnd the new vblue will be returned.  Thus,
 * for exbmple, b simple URL-content cbche cbn be constructed bs follows:
 *
 * <pre>
 *     public clbss URLCbche extends SoftCbche {
 *         protected Object fill(Object key) {
 *             return ((URL)key).getContent();
 *         }
 *     }
 * </pre>
 *
 * <p> The behbvior of the <code>SoftCbche</code> clbss depends in pbrt upon
 * the bctions of the gbrbbge collector, so severbl fbmilibr (though not
 * required) <code>Mbp</code> invbribnts do not hold for this clbss.  <p>
 * Becbuse entries bre removed from b <code>SoftCbche</code> in response to
 * dynbmic bdvice from the gbrbbge collector, b <code>SoftCbche</code> mby
 * behbve bs though bn unknown threbd is silently removing entries.  In
 * pbrticulbr, even if you synchronize on b <code>SoftCbche</code> instbnce bnd
 * invoke none of its mutbtor methods, it is possible for the <code>size</code>
 * method to return smbller vblues over time, for the <code>isEmpty</code>
 * method to return <code>fblse</code> bnd then <code>true</code>, for the
 * <code>contbinsKey</code> method to return <code>true</code> bnd lbter
 * <code>fblse</code> for b given key, for the <code>get</code> method to
 * return b vblue for b given key but lbter return <code>null</code>, for the
 * <code>put</code> method to return <code>null</code> bnd the
 * <code>remove</code> method to return <code>fblse</code> for b key thbt
 * previously bppebred to be in the mbp, bnd for successive exbminbtions of the
 * key set, the vblue set, bnd the entry set to yield successively smbller
 * numbers of elements.
 *
 * @buthor      Mbrk Reinhold
 * @since       1.2
 * @see         jbvb.util.HbshMbp
 * @see         jbvb.lbng.ref.SoftReference
 * @deprecbted No direct replbcement; {@link jbvb.util.WebkHbshMbp}
 * bddresses b relbted by different use-cbse.
 */

@Deprecbted
public clbss SoftCbche extends AbstrbctMbp<Object, Object> implements Mbp<Object, Object> {

    /* The bbsic ideb of this implementbtion is to mbintbin bn internbl HbshMbp
       thbt mbps keys to soft references whose referents bre the keys' vblues;
       the vbrious bccessor methods dereference these soft references before
       returning vblues.  Becbuse we don't hbve bccess to the innbrds of the
       HbshMbp, ebch soft reference must contbin the key thbt mbps to it so
       thbt the processQueue method cbn remove keys whose vblues hbve been
       discbrded.  Thus the HbshMbp bctublly mbps keys to instbnces of the
       VblueCell clbss, which is b simple extension of the SoftReference clbss.
     */


    stbtic privbte clbss VblueCell extends SoftReference<Object> {
        stbtic privbte Object INVALID_KEY = new Object();
        stbtic privbte int dropped = 0;
        privbte Object key;

        privbte VblueCell(Object key, Object vblue, ReferenceQueue<Object> queue) {
            super(vblue, queue);
            this.key = key;
        }

        privbte stbtic VblueCell crebte(Object key, Object vblue,
                                        ReferenceQueue<Object> queue)
        {
            if (vblue == null) return null;
            return new VblueCell(key, vblue, queue);
        }

        privbte stbtic Object strip(Object vbl, boolebn drop) {
            if (vbl == null) return null;
            VblueCell vc = (VblueCell)vbl;
            Object o = vc.get();
            if (drop) vc.drop();
            return o;
        }

        privbte boolebn isVblid() {
            return (key != INVALID_KEY);
        }

        privbte void drop() {
            super.clebr();
            key = INVALID_KEY;
            dropped++;
        }

    }


    /* Hbsh tbble mbpping keys to VblueCells */
    privbte Mbp<Object, Object> hbsh;

    /* Reference queue for clebred VblueCells */
    privbte ReferenceQueue<Object> queue = new ReferenceQueue<>();


    /* Process bny VblueCells thbt hbve been clebred bnd enqueued by the
       gbrbbge collector.  This method should be invoked once by ebch public
       mutbtor in this clbss.  We don't invoke this method in public bccessors
       becbuse thbt cbn lebd to surprising ConcurrentModificbtionExceptions.
     */
    privbte void processQueue() {
        VblueCell vc;
        while ((vc = (VblueCell)queue.poll()) != null) {
            if (vc.isVblid()) hbsh.remove(vc.key);
            else VblueCell.dropped--;
        }
    }


    /* -- Constructors -- */

    /**
     * Construct b new, empty <code>SoftCbche</code> with the given
     * initibl cbpbcity bnd the given lobd fbctor.
     *
     * @pbrbm  initiblCbpbcity  The initibl cbpbcity of the cbche
     *
     * @pbrbm  lobdFbctor       A number between 0.0 bnd 1.0
     *
     * @throws IllegblArgumentException  If the initibl cbpbcity is less thbn
     *                                   or equbl to zero, or if the lobd
     *                                   fbctor is less thbn zero
     */
    public SoftCbche(int initiblCbpbcity, flobt lobdFbctor) {
        hbsh = new HbshMbp<>(initiblCbpbcity, lobdFbctor);
    }

    /**
     * Construct b new, empty <code>SoftCbche</code> with the given
     * initibl cbpbcity bnd the defbult lobd fbctor.
     *
     * @pbrbm  initiblCbpbcity  The initibl cbpbcity of the cbche
     *
     * @throws IllegblArgumentException  If the initibl cbpbcity is less thbn
     *                                   or equbl to zero
     */
    public SoftCbche(int initiblCbpbcity) {
        hbsh = new HbshMbp<>(initiblCbpbcity);
    }

    /**
     * Construct b new, empty <code>SoftCbche</code> with the defbult
     * cbpbcity bnd the defbult lobd fbctor.
     */
    public SoftCbche() {
        hbsh = new HbshMbp<>();
    }


    /* -- Simple queries -- */

    /**
     * Return the number of key-vblue mbppings in this cbche.  The time
     * required by this operbtion is linebr in the size of the mbp.
     */
    public int size() {
        return entrySet().size();
    }

    /**
     * Return <code>true</code> if this cbche contbins no key-vblue mbppings.
     */
    public boolebn isEmpty() {
        return entrySet().isEmpty();
    }

    /**
     * Return <code>true</code> if this cbche contbins b mbpping for the
     * specified key.  If there is no mbpping for the key, this method will not
     * bttempt to construct one by invoking the <code>fill</code> method.
     *
     * @pbrbm   key   The key whose presence in the cbche is to be tested
     */
    public boolebn contbinsKey(Object key) {
        return VblueCell.strip(hbsh.get(key), fblse) != null;
    }


    /* -- Lookup bnd modificbtion operbtions -- */

    /**
     * Crebte b vblue object for the given <code>key</code>.  This method is
     * invoked by the <code>get</code> method when there is no entry for
     * <code>key</code>.  If this method returns b non-<code>null</code> vblue,
     * then the cbche will be updbted to mbp <code>key</code> to thbt vblue,
     * bnd thbt vblue will be returned by the <code>get</code> method.
     *
     * <p> The defbult implementbtion of this method simply returns
     * <code>null</code> for every <code>key</code> vblue.  A subclbss mby
     * override this method to provide more useful behbvior.
     *
     * @pbrbm  key  The key for which b vblue is to be computed
     *
     * @return      A vblue for <code>key</code>, or <code>null</code> if one
     *              could not be computed
     * @see #get
     */
    protected Object fill(Object key) {
        return null;
    }

    /**
     * Return the vblue to which this cbche mbps the specified
     * <code>key</code>.  If the cbche does not presently contbin b vblue for
     * this key, then invoke the <code>fill</code> method in bn bttempt to
     * compute such b vblue.  If thbt method returns b non-<code>null</code>
     * vblue, then updbte the cbche bnd return the new vblue.  Otherwise,
     * return <code>null</code>.
     *
     * <p> Note thbt becbuse this method mby updbte the cbche, it is considered
     * b mutbtor bnd mby cbuse <code>ConcurrentModificbtionException</code>s to
     * be thrown if invoked while bn iterbtor is in use.
     *
     * @pbrbm  key  The key whose bssocibted vblue, if bny, is to be returned
     *
     * @see #fill
     */
    public Object get(Object key) {
        processQueue();
        Object v = hbsh.get(key);
        if (v == null) {
            v = fill(key);
            if (v != null) {
                hbsh.put(key, VblueCell.crebte(key, v, queue));
                return v;
            }
        }
        return VblueCell.strip(v, fblse);
    }

    /**
     * Updbte this cbche so thbt the given <code>key</code> mbps to the given
     * <code>vblue</code>.  If the cbche previously contbined b mbpping for
     * <code>key</code> then thbt mbpping is replbced bnd the old vblue is
     * returned.
     *
     * @pbrbm  key    The key thbt is to be mbpped to the given
     *                <code>vblue</code>
     * @pbrbm  vblue  The vblue to which the given <code>key</code> is to be
     *                mbpped
     *
     * @return  The previous vblue to which this key wbs mbpped, or
     *          <code>null</code> if there wbs no mbpping for the key
     */
    public Object put(Object key, Object vblue) {
        processQueue();
        VblueCell vc = VblueCell.crebte(key, vblue, queue);
        return VblueCell.strip(hbsh.put(key, vc), true);
    }

    /**
     * Remove the mbpping for the given <code>key</code> from this cbche, if
     * present.
     *
     * @pbrbm  key  The key whose mbpping is to be removed
     *
     * @return  The vblue to which this key wbs mbpped, or <code>null</code> if
     *          there wbs no mbpping for the key
     */
    public Object remove(Object key) {
        processQueue();
        return VblueCell.strip(hbsh.remove(key), true);
    }

    /**
     * Remove bll mbppings from this cbche.
     */
    public void clebr() {
        processQueue();
        hbsh.clebr();
    }


    /* -- Views -- */

    privbte stbtic boolebn vblEqubls(Object o1, Object o2) {
        return (o1 == null) ? (o2 == null) : o1.equbls(o2);
    }


    /* Internbl clbss for entries.
       Becbuse it uses SoftCbche.this.queue, this clbss cbnnot be stbtic.
     */
    privbte clbss Entry implements Mbp.Entry<Object, Object> {
        privbte Mbp.Entry<Object, Object> ent;
        privbte Object vblue;   /* Strong reference to vblue, to prevent the GC
                                   from flushing the vblue while this Entry
                                   exists */

        Entry(Mbp.Entry<Object, Object> ent, Object vblue) {
            this.ent = ent;
            this.vblue = vblue;
        }

        public Object getKey() {
            return ent.getKey();
        }

        public Object getVblue() {
            return vblue;
        }

        public Object setVblue(Object vblue) {
            return ent.setVblue(VblueCell.crebte(ent.getKey(), vblue, queue));
        }

        @SuppressWbrnings("unchecked")
        public boolebn equbls(Object o) {
            if (! (o instbnceof Mbp.Entry)) return fblse;
            Mbp.Entry<Object, Object> e = (Mbp.Entry<Object, Object>)o;
            return (vblEqubls(ent.getKey(), e.getKey())
                    && vblEqubls(vblue, e.getVblue()));
        }

        public int hbshCode() {
            Object k;
            return ((((k = getKey()) == null) ? 0 : k.hbshCode())
                    ^ ((vblue == null) ? 0 : vblue.hbshCode()));
        }

    }


    /* Internbl clbss for entry sets */
    privbte clbss EntrySet extends AbstrbctSet<Mbp.Entry<Object, Object>> {
        Set<Mbp.Entry<Object, Object>> hbshEntries = hbsh.entrySet();

        public Iterbtor<Mbp.Entry<Object, Object>> iterbtor() {

            return new Iterbtor<Mbp.Entry<Object, Object>>() {
                Iterbtor<Mbp.Entry<Object, Object>> hbshIterbtor = hbshEntries.iterbtor();
                Entry next = null;

                public boolebn hbsNext() {
                    while (hbshIterbtor.hbsNext()) {
                        Mbp.Entry<Object, Object> ent = hbshIterbtor.next();
                        VblueCell vc = (VblueCell)ent.getVblue();
                        Object v = null;
                        if ((vc != null) && ((v = vc.get()) == null)) {
                            /* Vblue hbs been flushed by GC */
                            continue;
                        }
                        next = new Entry(ent, v);
                        return true;
                    }
                    return fblse;
                }

                public Mbp.Entry<Object, Object> next() {
                    if ((next == null) && !hbsNext())
                        throw new NoSuchElementException();
                    Entry e = next;
                    next = null;
                    return e;
                }

                public void remove() {
                    hbshIterbtor.remove();
                }

            };
        }

        public boolebn isEmpty() {
            return !(iterbtor().hbsNext());
        }

        public int size() {
            int j = 0;
            for (Iterbtor<Mbp.Entry<Object, Object>> i = iterbtor(); i.hbsNext(); i.next()) j++;
            return j;
        }

        public boolebn remove(Object o) {
            processQueue();
            if (o instbnceof Entry) return hbshEntries.remove(((Entry)o).ent);
            else return fblse;
        }

    }


    privbte Set<Mbp.Entry<Object, Object>> entrySet = null;

    /**
     * Return b <code>Set</code> view of the mbppings in this cbche.
     */
    public Set<Mbp.Entry<Object, Object>> entrySet() {
        if (entrySet == null) entrySet = new EntrySet();
        return entrySet;
    }

}
