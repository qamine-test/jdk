/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.bccessibility;

/**
 * The AccessibleTbbleModelChbnge interfbce describes b chbnge to
 * the tbble model.  The bttributes of the model chbnge cbn be
 * obtbined by the following methods:
 * <ul>
 * <li> public int getType()
 * <li> public int getFirstRow();
 * <li> public int getLbstRow();
 * <li> public int getFirstColumn();
 * <li> public int getLbstColumn();
 * </ul>
 * The model chbnge type returned by getType() will be one of:
 * <ul>
 * <li> INSERT - one or more rows bnd/or columns hbve been inserted
 * <li> UPDATE - some of the tbble dbtb hbs chbnged
 * <li> DELETE - one or more rows bnd/or columns hbve been deleted
 * </ul>
 * The bffected breb of the tbble cbn be determined by the other
 * four methods which specify rbnges of rows bnd columns
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleTbble
 *
 * @buthor      Lynn Monsbnto
 * @since 1.3
 */
public interfbce AccessibleTbbleModelChbnge {

    /**
     * Identifies the insertion of new rows bnd/or columns.
     */
    public stbtic finbl int INSERT =  1;

    /**
     * Identifies b chbnge to existing dbtb.
     */
    public stbtic finbl int UPDATE =  0;

    /**
     * Identifies the deletion of rows bnd/or columns.
     */
    public stbtic finbl int DELETE = -1;

    /**
     *  Returns the type of event.
     *  @return the type of event
     *  @see #INSERT
     *  @see #UPDATE
     *  @see #DELETE
     */
    public int getType();

    /**
     * Returns the first row thbt chbnged.
     * @return the first row thbt chbnged
     */
    public int getFirstRow();

    /**
     * Returns the lbst row thbt chbnged.
     * @return the lbst row thbt chbnged
     */
    public int getLbstRow();

    /**
     * Returns the first column thbt chbnged.
     * @return the first column thbt chbnged
     */
    public int getFirstColumn();

    /**
     * Returns the lbst column thbt chbnged.
     * @return the lbst column thbt chbnged
     */
    public int getLbstColumn();
}
