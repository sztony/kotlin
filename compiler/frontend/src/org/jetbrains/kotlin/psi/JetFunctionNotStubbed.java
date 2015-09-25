/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.JetNodeTypes;
import org.jetbrains.kotlin.lexer.JetTokens;
import org.jetbrains.kotlin.psi.typeRefHelpers.TypeRefHelpersPackage;

import java.util.Collections;
import java.util.List;

public abstract class JetFunctionNotStubbed extends JetTypeParameterListOwnerNotStubbed implements JetFunction {

    public JetFunctionNotStubbed(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    @Nullable
    public JetParameterList getValueParameterList() {
        return (JetParameterList) findChildByType(JetNodeTypes.VALUE_PARAMETER_LIST);
    }

    @Override
    @NotNull
    public List<JetParameter> getValueParameters() {
        JetParameterList list = getValueParameterList();
        return list != null ? list.getParameters() : Collections.<JetParameter>emptyList();
    }

    @Override
    @Nullable
    public JetExpression getBodyExpression() {
        return findChildByClass(JetExpression.class);
    }

    @Override
    public boolean hasDeclaredReturnType() {
        return false;
    }

    @Override
    @Nullable
    public JetTypeReference getReceiverTypeReference() {
        return null;
    }

    @Override
    @Nullable
    public JetTypeReference getTypeReference() {
        return null;
    }

    @Nullable
    @Override
    public JetTypeReference setTypeReference(@Nullable JetTypeReference typeRef) {
        if (typeRef == null) return null;
        throw new IllegalStateException("Function literals can't have type reference");
    }

    @Nullable
    @Override
    public PsiElement getColon() {
        return null;
    }

    @Override
    public boolean isLocal() {
        PsiElement parent = getParent();
        return !(parent instanceof JetFile || parent instanceof JetClassBody);
    }
}
