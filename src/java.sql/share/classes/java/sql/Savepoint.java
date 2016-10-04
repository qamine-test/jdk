/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.sql;

/**
 * The representbtion of b sbvepoint, which is b point within
 * the current trbnsbction thbt cbn be referenced from the
 * <code>Connection.rollbbck</code> method. When b trbnsbction
 * is rolled bbck to b sbvepoint bll chbnges mbde bfter thbt
 * sbvepoint bre undone.
 * <p>
 * Sbvepoints cbn be either nbmed or unnbmed. Unnbmed sbvepoints
 * bre identified by bn ID generbted by the underlying dbtb source.
 *
 * @since 1.4
 */

public interfbce Sbvepoint {

    /**
     * Retrieves the generbted ID for the sbvepoint thbt this
     * <code>Sbvepoint</code> object represents.
     * @return the numeric ID of this sbvepoint
     * @exception SQLException if this is b nbmed sbvepoint
     * @since 1.4
     */
    int getSbvepointId() throws SQLException;

    /**
     * Retrieves the nbme of the sbvepoint thbt this <code>Sbvepoint</code>
     * object represents.
     * @return the nbme of this sbvepoint
     * @exception SQLException if this is bn un-nbmed sbvepoint
     * @since 1.4
     */
    String getSbvepointNbme() throws SQLException;
}
