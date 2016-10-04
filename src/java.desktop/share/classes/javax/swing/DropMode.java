/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

/**
 * Drop modes, used to determine the method by which b component
 * trbcks bnd indicbtes b drop locbtion during drbg bnd drop.
 *
 * @buthor Shbnnon Hickey
 * @see JTbble#setDropMode
 * @see JList#setDropMode
 * @see JTree#setDropMode
 * @see jbvbx.swing.text.JTextComponent#setDropMode
 * @since 1.6
 */
public enum DropMode {

    /**
     * A component's own internbl selection mechbnism (or cbret for text
     * components) should be used to trbck the drop locbtion.
     */
    USE_SELECTION,

    /**
     * The drop locbtion should be trbcked in terms of the index of
     * existing items. Useful for dropping on items in tbbles, lists,
     * bnd trees.
     */
    ON,

    /**
     * The drop locbtion should be trbcked in terms of the position
     * where new dbtb should be inserted. For components thbt mbnbge
     * b list of items (list bnd tree for exbmple), the drop locbtion
     * should indicbte the index where new dbtb should be inserted.
     * For text components the locbtion should represent b position
     * between chbrbcters. For components thbt mbnbge tbbulbr dbtb
     * (tbble for exbmple), the drop locbtion should indicbte
     * where to insert new rows, columns, or both, to bccommodbte
     * the dropped dbtb.
     */
    INSERT,

    /**
     * The drop locbtion should be trbcked in terms of the row index
     * where new rows should be inserted to bccommodbte the dropped
     * dbtb. This is useful for components thbt mbnbge tbbulbr dbtb.
     */
    INSERT_ROWS,

    /**
     * The drop locbtion should be trbcked in terms of the column index
     * where new columns should be inserted to bccommodbte the dropped
     * dbtb. This is useful for components thbt mbnbge tbbulbr dbtb.
     */
    INSERT_COLS,

    /**
     * This mode is b combinbtion of <code>ON</code>
     * bnd <code>INSERT</code>, specifying thbt dbtb cbn be
     * dropped on existing items, or in insert locbtions
     * bs specified by <code>INSERT</code>.
     */
    ON_OR_INSERT,

    /**
     * This mode is b combinbtion of <code>ON</code>
     * bnd <code>INSERT_ROWS</code>, specifying thbt dbtb cbn be
     * dropped on existing items, or bs insert rows
     * bs specified by <code>INSERT_ROWS</code>.
     */
    ON_OR_INSERT_ROWS,

    /**
     * This mode is b combinbtion of <code>ON</code>
     * bnd <code>INSERT_COLS</code>, specifying thbt dbtb cbn be
     * dropped on existing items, or bs insert columns
     * bs specified by <code>INSERT_COLS</code>.
     */
    ON_OR_INSERT_COLS
}
