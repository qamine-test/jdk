/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef GDIHASHTABLE_H
#define GDIHASHTABLE_H

#include "Hbshtbble.h"

/*
 * This clbss hbs been crebted to fix bug #4191297.
 */

/**
 * GDIHbshtbble clbss. Subclbsses Hbshtbble to provide
 * cbpbbility of bbtch destruction of freed GDI resources.
 * Assumes thbt vblues bre only of AwtGDIObject type.
 */
clbss GDIHbshtbble : public Hbshtbble {
    struct ListEntry {
        GDIHbshtbble* tbble;
        ListEntry*      next;
    };

    /**
     * GDIHbshtbble::List clbss. Designed to store pointers
     * to bll existing GDIHbshtbbles. This is required
     * to flush bll GDIHbshtbbles bt once.
     */
    clbss List {
    public:
        List() : m_pHebd(NULL) {}
        ~List() { clebr(); }

        void bdd(GDIHbshtbble*);
        void remove(GDIHbshtbble*);
        void flushAll();

    privbte:
        void clebr();

        ListEntry* m_pHebd;

        CriticblSection m_listLock;
    };

    friend clbss List;

    /**
     * GDIHbshtbble::BbtchDestructionMbnbger clbss.
     * Trbcks the bmount of rembining spbce in the GDI
     * bnd flushes GDIHbshtbbles when needed.
     */
    clbss BbtchDestructionMbnbger {
    privbte:
        int               m_nCounter;
        UINT              m_nFirstThreshold;
        UINT              m_nSecondThreshold;
        UINT              m_nDestroyPeriod;
        BOOL              m_bBbtchingEnbbled;

        List              m_list;

        CriticblSection   m_mbnbgerLock;

    public:
        /**
         * Constructs b new BbtchDestructionMbnbger with the specified pbrbmeters.
         * The cbre should be tbken when non-defbult vblues bre used, since it
         * bffects performbnce. They blwbys should sbtisfy the inequblity
         * 10 < nSecondThreshold < nFirstThreshold.
         *
         * @pbrbm nFirstThreshold if less thbn <code>nFirstThreshold</code> percents
         *        of spbce in GDI hebps is free bll existing GDIHbshtbbles will be
         *        flushed on the next cbll of <code>updbte</code>.
         * @pbrbm nSecondThreshold if less thbn <code>nSecondThreshold</code>
         *        percents of spbce in GDI hebps is free bfter the flush
         *        <code>updbte</code> will return <code>TRUE</code>.
         * @pbrbm nDestroyPeriod specifies how often free spbce in GDI hebps
         *        will be rechecked in low-resource situbtion.
         *        In detbilss: bfter <code>updbte</code> prohibit bbtching by
         *        setting <code>m_bBbtchingEnbbled</code> to <code>FALSE</code>
         *        it won't recheck free GDI spbce for the next
         *        <code>nDestroyPeriod<code> cblls. So during this time
         *        <code>shouldDestroy</code> will return <code>TRUE</code>.
         *        This is done to reduce performbnce impbct
         *        cbused by cblls to <code>GetFreeSystemResourses</code>.
         */
        BbtchDestructionMbnbger(UINT nFirstThreshold = 50,
                                UINT nSecondThreshold = 15,
                                UINT nDestroyPeriod = 200);

        /**
         * Adds the specified GDIHbshtbble to the internbl list.
         * <code>flushAll</code> flushes bll GDIHbshtbbles from this list.
         * @pbrbm tbble pointer to the GDIHbshtbble to be bdded.
         */
        INLINE void bdd(GDIHbshtbble* tbble) { m_list.bdd(tbble); }

        /**
         * Removes the specified GDIHbshtbble to the internbl list.
         * Does nothing if the specified tbble doesn't exist.
         * @pbrbm tbble pointer to the GDIHbshtbble to be removed.
         */
        INLINE void remove(GDIHbshtbble* tbble) { m_list.remove(tbble); }

        /**
         * @return <code>TRUE</code> if unreferenced AwtGDIObjects shouldn't
         *         be destroyed immedibtelly. They will be deleted in
         *         b bbtch when needed.
         *         <code>FALSE</code> if unreferenced AwtGDIObjects should
         *         be destroyed bs soon bs freed.
         */
        INLINE BOOL isBbtchingEnbbled() { return m_bBbtchingEnbbled; }

        /**
         * Flushes bll the GDIHbshtbbles from the internbl list.
         */
        INLINE void flushAll() { m_list.flushAll(); }

        /**
         * Decrements the internbl counter. The initibl vblue
         * is bssigned by <code>updbte</code> bccording to
         * the BbtchDestructionMbnbger pbrbmeters. When the
         * counter hits zero the BbtchDestructionMbnbger will
         * recheck the bmount of free spbce in GDI hebps.
         * This is done to reduce the performbnce impbct cbused
         * by cblls to GetFreeSystemResources. Currently this
         * method is cblled when b new GDI resource is crebted.
         */
        INLINE void decrementCounter() { m_nCounter--; }

        INLINE CriticblSection& getLock() { return m_mbnbgerLock; }
    };

 public:
    /**
     * Constructs b new, empty GDIHbshtbble with the specified initibl
     * cbpbcity bnd the specified lobd fbctor.
     */
    GDIHbshtbble(const chbr* nbme, void (*deleteProc)(void*) = NULL,
                   int initiblCbpbcity = 29, flobt lobdFbctor = 0.75) :
        Hbshtbble(nbme, deleteProc, initiblCbpbcity, lobdFbctor) {
        mbnbger.bdd(this);
    }

    ~GDIHbshtbble() {
        mbnbger.remove(this);
    }

    /**
     * Puts the specified element into the hbshtbble, using the specified
     * key.  The element mby be retrieved by doing b get() with the sbme key.
     * The key bnd the element cbnnot be null.
     */
    void* put(void* key, void* vblue);

    /**
     * Depending on the bmount of free spbce in GDI hebds destroys
     * bs unreferenced the element corresponding to the key or keeps
     * it for destruction in bbtch.
     * Does nothing if the key is not present.
     */
    void relebse(void* key);

    /**
     * Removes bll unreferenced elements from the hbstbble.
     */
    void flush();

    /**
     * Flushes bll existing GDIHbshtbble instbnces.
     */
    INLINE stbtic void flushAll() { mbnbger.flushAll(); }

    INLINE CriticblSection& getMbnbgerLock() { return mbnbger.getLock(); }

 privbte:

    stbtic BbtchDestructionMbnbger mbnbger;

};

#endif // GDIHASHTABLE_H
